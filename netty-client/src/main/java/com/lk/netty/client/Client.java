package com.lk.netty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import com.lk.netty.client.config.ClientConfigs;
import com.lk.netty.client.handler.ClientTransportHandler;
import com.lk.netty.client.packet.PacketType;
import com.lk.netty.client.packet.coder.PacketDecoder;
import com.lk.netty.client.packet.coder.PacketEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 *  �ͻ���������
 * 2019��3��8��
 * likai
 */
public class Client {
	
	//��ǰ��������
	private int reconnectTimes = 0;
	
	private ClientConfigs config = new ClientConfigs();
	
	public void start() {
		try{
			PacketType.initPackets();
			connect(config.getRemoteServerIp(),
					config.getRemoteServerPort());
		}catch(Exception e){
 
		}
	}
 
	public void connect(String host,int port) throws Exception {  
		EventLoopGroup group = new NioEventLoopGroup(1);  
		try{  
			Bootstrap b  = new Bootstrap();  
			b.group(group).channel(NioSocketChannel.class)
			.remoteAddress(new InetSocketAddress(host, port))
			.handler(new ChannelInitializer<SocketChannel>(){  
 
				@Override  
				protected void initChannel(SocketChannel arg0)  
						throws Exception {  
					ChannelPipeline pipeline = arg0.pipeline();  
					pipeline.addLast(new PacketDecoder(1024*1, 0,4,0,4));  
					pipeline.addLast(new LengthFieldPrepender(4));  
					pipeline.addLast(new PacketEncoder());
//					pipeline.addLast(new StringDecoder());
//					pipeline.addLast(new StringEncoder());
					pipeline.addLast(new ClientTransportHandler());  
				}
			});
			
			b.connect().sync();
		}catch(Exception e){  
			e.printStackTrace();  
			//          group.shutdownGracefully();  //���ﲻ�������Źر���  
			//�������������������ֹ����������رյ��µĿ�ѭ��
			if (reconnectTimes < config.getMaxReconnectTimes()) {
				reConnectServer();  
			}else {
				group.shutdownGracefully();
			}
		}finally{  
		}  
	}
	
	/**
	 * ��������
	 */
	private void reConnectServer(){
		try {
			Thread.sleep(5000);
			System.err.println("�ͻ��˽��ж�������");
			connect(config.getRemoteServerIp(),
					config.getRemoteServerPort());
			reconnectTimes++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������������
	 */
	public void resetReconnectTimes() {
		if (reconnectTimes > 0) {
			reconnectTimes = 0;
			System.err.println("���������ɹ�");
		}
	}
	
	public static void main(String[] args) {
		new Client().start();
	}
}
