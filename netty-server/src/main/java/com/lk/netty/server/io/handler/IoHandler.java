package com.lk.netty.server.io.handler;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lk.netty.server.io.util.ChannelUtils;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.ChannelManager;
import com.lk.netty.server.manager.PacketManager;
import com.lk.netty.server.packet.AbstractPacket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 *   信息通道处理工具
 * 2019年3月11日
 * likai
 */
public class IoHandler extends ChannelInboundHandlerAdapter{
	
	private final static Logger logger = LoggerFactory.getLogger(IoHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//ChannelManager.registerChannel(ctx.channel());
		if (!ChannelUtils.addChannelSession(ctx.channel(), new IoSession(ctx.channel()))) {
			ctx.channel().close();
			logger.error("Duplicate session,IP=[{}]",ChannelUtils.getIp(ctx.channel()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext context,Object msg)
			throws Exception {
		AbstractPacket message = (AbstractPacket)msg;
		logger.info("receive pact, content is {}", message.getClass().getSimpleName());

		final Channel channel = context.channel();
		IoSession session = ChannelUtils.getSessionBy(channel);

		logger.info(ChannelUtils.getSessionBy(channel).getIpAddr() + ":" + message.toString());
		// 不在io线程处理
		PacketManager.INSTANCE.execPacket(session, message);

		// 扔到业务线程池处理
//		CmdTask cmdTask = CmdTask.valueOf(session.getDispatchKey(), session, message);
//		SpringContext.getMessageDispatcher().addMessageTask(cmdTask);
		
//		String message = (String)msg;
//		Channel fromChannel = context.channel();
//		String fromIp = ((InetSocketAddress)fromChannel.remoteAddress()).getAddress().toString().substring(1) + ((InetSocketAddress)fromChannel.remoteAddress()).getPort();
//		logger.info("服务端收到的消息：" + message);
//		if(StringUtils.isNotEmpty(message)) {
//			String[] messageArry = message.split("#");
//			String toIp = messageArry[0];
//			String content = messageArry[1];
//			Channel toChannel = ChannelManager.getChannelByRemoteIp(toIp);
//			if(toChannel == null) {
//				fromChannel.writeAndFlush(toIp +"不在线！");
//			}else {				
//				toChannel.writeAndFlush("来自" + fromIp + "的消息: " + content);
//			}
//		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("业务逻辑出错", cause);
		cause.printStackTrace();
		Channel channel = ctx.channel();
		ChannelManager.cancelChannel(channel);
		if(cause instanceof IOException && channel.isActive()){
			logger.error("simpleclient"+channel.remoteAddress()+"异常");
			//SpringContext.getUserService().userLogout(channel, SessionCloseReason.NORMAL);
			ctx.close();
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		//心跳包检测读超时
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.ALL_IDLE) {
				logger.info("客户端读超时");
				Channel channel = ctx.channel();
				ChannelManager.cancelChannel(channel);
				logger.info(ChannelUtils.getSessionBy(channel).getIpAddr() + "已下线！");
				ctx.close();
				//SpringContext.getUserService().userLogout(channel, SessionCloseReason.OVER_TIME);
			}
		}
	}
}
