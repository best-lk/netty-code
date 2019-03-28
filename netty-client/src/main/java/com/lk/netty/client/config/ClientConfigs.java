package com.lk.netty.client.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  �ͻ���������
 * 2019��3��8��
 * likai
 */
public class ClientConfigs {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientConfigs.class);
	
	//Զ�̷���IP��ַ
	public static final String REMOTE_SERVER_IP = "remote_server_ip";
	
	//Զ�̷���˿�
	public static final String REMOTE_SERVER_PORT = "remote_server_port";
	
	//���ص�ַ
	public static final String LOCAL_SERVER_IP = "local_server_ip";
	
	//���ض˿�
	public static final String LOCAL_SERVER_PORT = "local_server_port";
	
	//�����������
	public static final String MAX_RECONNECT_TIMES = "max_reconnect_times";
	
	//�����ļ�handler
	public static Properties pro = new Properties();
	
	//Զ�̷����ַ
	private String remoteServerIp;
	
	//Զ�̷���˿�
	private Integer remoteServerPort;
	
	//�ͻ���ip��ַ
	private String localServerIp;
	
	//�ͻ��˶˿�
	private Integer localServerPort;
	
	//�����������
	private Integer maxReconnectTimes;

	public ClientConfigs(){
		try{
			InputStream in = ClientConfigs.class.getResourceAsStream("/client.properties");
			pro.load(in);
		}catch(IOException e) {
			logger.error("��ȡ�ͻ��������ļ�����", e);
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
