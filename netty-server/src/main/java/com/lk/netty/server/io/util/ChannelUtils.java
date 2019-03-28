package com.lk.netty.server.io.util;

import java.net.InetSocketAddress;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * 通信通道的管理工具
 * 2019年3月7日
 * likai
 */
public class ChannelUtils {
	
	public static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");
	
	/**
	 * 添加新的会话
	 * @param channel
	 * @param session
	 * @return
	 */
	public static boolean addChannelSession(Channel channel, IoSession session) {
		Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
		return sessionAttr.compareAndSet(null, session);
	}
	
	/**
	 * 获取session
	 * @param channel
	 * @return
	 * 2019年3月7日
	 * likai
	 */
	public static IoSession getSessionBy(Channel channel) {
		Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
		return sessionAttr.get() ;
	}
	
	/**
	 * 获取channel客户端ip
	 * @param channel
	 * @return
	 * 2019年3月7日
	 * likai
	 */
	public static String getIp(Channel channel) {
		return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1);
	}
}
