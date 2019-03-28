package com.lk.netty.server.io.handler;

import com.lk.netty.server.coder.PacketDecoder;
import com.lk.netty.server.coder.PacketEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 *  �Զ����ʼ��handler����
 * 2019��3��11��
 * likai
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
	
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();
		pipeline.addLast(new PacketDecoder(1024 * 4, 0, 4, 0, 4));
		pipeline.addLast(new LengthFieldPrepender(4));
		pipeline.addLast(new PacketEncoder());
		// �ͻ���300��û�շ�������ᴥ��UserEventTriggered�¼���MessageTransportHandler
		pipeline.addLast("idleStateHandler", new IdleStateHandler(0, 0, 300));
		pipeline.addLast(new IoHandler());
	}
}
