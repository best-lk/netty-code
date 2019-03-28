package com.lk.netty.server.manager;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.netty.channel.Channel;

/**
 * channel�Ĺ�����
 * 2019��3��12��
 * likai
 */
public class ChannelManager {
	
	//channel�Ļ������
	public static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();
	
	/**
	 * ע��channel
	 * @param channel
	 * 2019��3��12��
	 * likai
	 */
	public static void registerChannel(Channel channel) {
		channelMap.put(((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1) + ((InetSocketAddress)channel.remoteAddress()).getPort(), channel);
	}
	
	/**
	 * ע��channel
	 * @param channel
	 * 2019��3��12��
	 * likai
	 */
	public static void cancelChannel(Channel channel) {
		channelMap.remove(((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1) + ((InetSocketAddress)channel.remoteAddress()).getPort());
	}
	
	/**
	 * ��ȡע���channel
	 * IP + port ��Ϊһ�����ӵı�ʶ
	 * @param remoteIp
	 * @return
	 * 2019��3��12��
	 * likai
	 */
	public static Channel getChannelByRemoteIp(String remoteIp) {
		return channelMap.get(remoteIp);
	}
}
