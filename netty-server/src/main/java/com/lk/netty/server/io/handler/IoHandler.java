package com.lk.netty.server.io.handler;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lk.netty.server.constant.SessionCloseReason;
import com.lk.netty.server.io.util.ChannelUtils;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.PacketManager;
import com.lk.netty.server.manager.SessionManager;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.service.UserInfoService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 *   ��Ϣͨ��������
 * 2019��3��11��
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
		// ����io�̴߳���
		PacketManager.INSTANCE.execPacket(session, message);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("ҵ���߼�����", cause);
		cause.printStackTrace();
		Channel channel = ctx.channel();
		if(cause instanceof IOException && channel.isActive()){
			logger.error("simpleclient"+channel.remoteAddress()+"�쳣");
			//SpringContext.getUserService().userLogout(channel, SessionCloseReason.NORMAL);
			SessionManager.INSTANCE.ungisterUserContext(channel, SessionCloseReason.NORMAL);
			UserInfoService.notifyAllUserLogin();
			ctx.close();
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		//������������ʱ
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.ALL_IDLE) {
				logger.info("�ͻ��˶���ʱ");
				Channel channel = ctx.channel();
				logger.info(ChannelUtils.getSessionBy(channel).getIpAddr() + "�����ߣ�");
				ctx.close();
				//SpringContext.getUserService().userLogout(channel, SessionCloseReason.OVER_TIME);
			}
		}
	}
}
