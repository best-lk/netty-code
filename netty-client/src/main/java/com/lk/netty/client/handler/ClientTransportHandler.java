package com.lk.netty.client.handler;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketManager;
import com.lk.netty.client.session.SessionManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *  �ͻ�����Ϣ�����߼�
 * 2019��3��8��
 * likai
 */
public class ClientTransportHandler extends ChannelInboundHandlerAdapter{
	
	public ClientTransportHandler(){

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx){
		//ע��session
		SessionManager.INSTANCE.registerSession(ctx.channel());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception{
		AbstractPacket  packet = (AbstractPacket)msg;
		PacketManager.INSTANCE.execPacket(packet);
//		String message = (String)msg;
//		ctx.channel().writeAndFlush("���յ��˷���˵Ļ�Ӧ��");
//	    System.out.println(message);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("�ͻ��˹ر�1");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("�ͻ��˹ر�3");
		Channel channel = ctx.channel();
		cause.printStackTrace();
		if(channel.isActive()){
			System.err.println("simpleclient"+channel.remoteAddress()+"�쳣");
		}
	}
}
