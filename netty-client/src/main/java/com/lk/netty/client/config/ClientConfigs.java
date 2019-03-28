package com.lk.netty.client.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  客户端配置类
 * 2019年3月8日
 * likai
 */
public class ClientConfigs {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientConfigs.class);
	
	//远程服务IP地址
	public static final String REMOTE_SERVER_IP = "remote_server_ip";
	
	//远程服务端口
	public static final String REMOTE_SERVER_PORT = "remote_server_port";
	
	//本地地址
	public static final String LOCAL_SERVER_IP = "local_server_ip";
	
	//本地端口
	public static final String LOCAL_SERVER_PORT = "local_server_port";
	
	//最大重连次数
	public static final String MAX_RECONNECT_TIMES = "max_reconnect_times";
	
	//配置文件handler
	public static Properties pro = new Properties();
	
	//远程服务地址
	private String remoteServerIp;
	
	//远程服务端口
	private Integer remoteServerPort;
	
	//客户端ip地址
	private String localServerIp;
	
	//客户端端口
	private Integer localServerPort;
	
	//最大重连次数
	private Integer maxReconnectTimes;

	public ClientConfigs(){
		try{
			InputStream in = ClientConfigs.class.getResourceAsStream("/client.properties");
			pro.load(in);
		}catch(IOException e) {
			logger.error("读取客户端配置文件错误！", e);
		}
		this.remoteServerIp = pro.getProperty(REMOTE_SERVER_IP);
		this.remoteServerPort = Integer.valueOf(pro.getProperty(REMOTE_SERVER_PORT));
		this.localServerIp = pro.getProperty(LOCAL_SERVER_IP);
		this.localServerPort = Integer.valueOf(pro.getProperty(LOCAL_SERVER_PORT));
		this.maxReconnectTimes = Integer.valueOf(pro.getProperty(MAX_RECONNECT_TIMES));
	}

	public String getRemoteServerIp() {
		return remoteServerIp;
	}

	public Integer getRemoteServerPort() {
		return remoteServerPort;
	}

	public String getLocalServerIp() {
		return localServerIp;
	}


	public Integer getLocalServerPort() {
		return localServerPort;
	}

	public Integer getMaxReconnectTimes() {
		return maxReconnectTimes;
	}
	
	@Override
	public String toString() {
		return "ClientConfigs [remoteServerIp=" + remoteServerIp + ", remoteServerPort=" + remoteServerPort
				+ ", localServerIp=" + localServerIp + ", localServerPort=" + localServerPort + ", maxReconnectTimes="
				+ maxReconnectTimes + "]";
	}
}
