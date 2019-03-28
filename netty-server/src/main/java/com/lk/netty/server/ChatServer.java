package com.lk.netty.server;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lk.netty.server.config.ServerConfigs;
import com.lk.netty.server.io.handler.ChildChannelHandler;
import com.lk.netty.server.packet.PacketType;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *  ������������
 * 2019��3��11��
 * likai
 */
public class ChatServer {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatServer.class);

	// ����ʹ��Ĭ���߳�������
	private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	private EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

	private int port = ServerConfigs.SERVER_PORT;

	
	public void start() throws Exception {
		logger.info("����������������ڼ����û�������......");
		// Э���ʼ��
		PacketType.initPackets();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childHandler(new ChildChannelHandler());

			b.bind(new InetSocketAddress(port)).sync();
		} catch (Exception e) {
			logger.error("", e);
			shutDown();
		}
	}

	public void shutDown() throws Exception {
		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
		}
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
		}
	}
	
	/**
	 * �������
	 * @param args
	 * 2019��3��11��
	 * likai
	 */
	public static void main(String[] args) {
		try {
			new ChatServer().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
