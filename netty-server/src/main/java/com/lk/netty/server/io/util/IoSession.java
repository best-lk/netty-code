package com.lk.netty.server.io.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lk.netty.server.constant.SessionCloseReason;
import com.lk.netty.server.info.User;
import com.lk.netty.server.packet.AbstractPacket;
import io.netty.channel.Channel;

/**
 * session����
 * 2019��3��11��
 * likai
 */
public class IoSession {
	private static final Logger logger = LoggerFactory.getLogger(IoSession.class);

	/** distributeKey auto generator */
	private AtomicInteger dispatchKeyGenerator = new AtomicInteger();

	/** ��������channel */
	private Channel channel;

	private User user;

	/** ip��ַ */
	private String ipAddr;

	private boolean reconnected;

	/** ҵ��ַ����� */
	private int dispatchKey;

	/** ��չ�ã�����һЩ�������� */
	private Map<String, Object> attrs = new HashMap<>();

	public IoSession() {

	}

	public IoSession(Channel channel) {
		this.channel = channel;
		this.ipAddr = ChannelUtils.getIp(channel);
		this.dispatchKey = dispatchKeyGenerator.getAndIncrement();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Channel getChannel() {
		return channel;
	}

	public int getDispatchKey() {
		return dispatchKey;
	}

	/**
	 * ��ͻ��˷�����Ϣ
	 * 
	 * @param packet
	 */
	public void sendPacket(AbstractPacket packet) {
		if (packet == null) {
			return;
		}
		if (channel != null) {
			channel.writeAndFlush(packet);
		}
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public boolean isReconnected() {
		return reconnected;
	}

	public void setReconnected(boolean reconnected) {
		this.reconnected = reconnected;
	}

	public User getUser() {
		return user;
	}

	public boolean isClose() {
		if (channel == null) {
			return true;
		}
		return !channel.isActive() || !channel.isOpen();
	}

	/**
	 * �ر�session
	 * 
	 * @param reason {@link SessionCloseReason}
	 */
	public void close(SessionCloseReason reason) {
		try {
			if (this.channel == null) {
				return;
			}
			if (channel.isOpen()) {
				channel.close();
				logger.info("close session[{}], reason is {}", getUser().getUserId(), reason);
			} else {
				logger.info("session[{}] already close, reason is {}", getUser().getUserId(), reason);
			}
		} catch (Exception e) {
		}
	}
}
