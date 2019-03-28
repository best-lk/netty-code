package com.lk.netty.server.io.util;

import java.net.InetSocketAddress;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * ͨ��ͨ���Ĺ�����
 * 2019��3��7��
 * likai
 */
public class ChannelUtils {
	
	public static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");
	
	/**
	 * ����µĻỰ
	 * @param channel
	 * @param session
	 * @return
	 */
	public static boolean addChannelSession(Channel channel, IoSession session) {
		Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
		return sessionAttr.compareAndSet(null, session);
	}
	
	/**
	 * ��ȡsession
	 * @param channel
	 * @return
	 * 2019��3��7��
	 * likai
	 */
	public static IoSession getSessionBy(Channel channel) {
		Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
		return sessionAttr.get() ;
	}
	
	/**
	 * ��ȡchannel�ͻ���ip
	 * @param channel
	 * @return
	 * 2019��3��7��
	 * likai
	 */
	public static String getIp(Channel channel) {
		return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1);
	}
}
