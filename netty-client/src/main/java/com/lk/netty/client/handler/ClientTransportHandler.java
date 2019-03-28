package com.lk.netty.client.handler;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketManager;
import com.lk.netty.client.session.SessionManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *  客户端消息处理逻辑
 * 2019年3月8日
 * likai
 */
public class ClientTransportHandler extends ChannelInboundHandlerAdapter{
	
	public ClientTransportHandler(){

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx){
		//注册session
		SessionManager.INSTANCE.registerSession(ctx.channel());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception{
		AbstractPacket  packet = (AbstractPacket)msg;
		PacketManager.INSTANCE.execPacket(packet);
//		String message = (String)msg;
//		ctx.channel().writeAndFlush("我收到了服务端的回应！");
//	    System.out.println(message);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("客户端关闭1");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("客户端关闭3");
		Channel channel = ctx.channel();
		cause.printStackTrace();
		if(channel.isActive()){
			System.err.println("simpleclient"+channel.remoteAddress()+"异常");
		}
	}
}
