package com.lk.netty.server.manager;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.netty.channel.Channel;

/**
 * channel的管理工具
 * 2019年3月12日
 * likai
 */
public class ChannelManager {
	
	//channel的缓存机制
	public static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();
	
	/**
	 * 注册channel
	 * @param channel
	 * 2019年3月12日
	 * likai
	 */
	public static void registerChannel(Channel channel) {
		channelMap.put(((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1) + ((InetSocketAddress)channel.remoteAddress()).getPort(), channel);
	}
	
	/**
	 * 注销channel
	 * @param channel
	 * 2019年3月12日
	 * likai
	 */
	public static void cancelChannel(Channel channel) {
		channelMap.remove(((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1) + ((InetSocketAddress)channel.remoteAddress()).getPort());
	}
	
	/**
	 * 获取注册的channel
	 * IP + port 作为一个连接的标识
	 * @param remoteIp
	 * @return
	 * 2019年3月12日
	 * likai
	 */
	public static Channel getChannelByRemoteIp(String remoteIp) {
		return channelMap.get(remoteIp);
	}
}
