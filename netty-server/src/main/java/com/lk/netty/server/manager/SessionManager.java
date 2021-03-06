package com.lk.netty.server.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lk.netty.server.constant.SessionCloseReason;
import com.lk.netty.server.info.User;
import com.lk.netty.server.io.util.ChannelUtils;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.service.UserInfoService;

import io.netty.channel.Channel;

public enum SessionManager {

	INSTANCE;

	private Logger logger = LoggerFactory.getLogger(SessionManager.class);

	/** 缓存通信上下文环境对应的登录用户（主要用于服务） */
	private Map<IoSession, String> session2UserIds  = new ConcurrentHashMap<>();

	/** 缓存用户id与对应的会话 */
	private ConcurrentMap<String, IoSession> userId2Sessions = new ConcurrentHashMap<>();


	/**
	 *  向单一在线用户发送数据包
	 */
	public void sendPacketTo(IoSession session, AbstractPacket pact) {
		if(pact == null || session == null) return;
		session.sendPacket(pact);
	}

	public void sendPacketTo(User user, AbstractPacket pact) {
		IoSession session = userId2Sessions.get(user.getUserId());
		if (session != null) {
			session.sendPacket(pact);
		}
	}

	public void sendPacketTo(Long userId, AbstractPacket pact) {
		if(pact == null || userId <= 0) return;

		IoSession session = userId2Sessions.get(userId);
		if (session != null) {
			session.sendPacket(pact);
		}
	}

	public void sendPacketTo(Channel channel, AbstractPacket pact) {
		if(pact == null || channel == null) return;
		channel.writeAndFlush(pact);
	}

	/**
	 *  向所有在线用户发送数据包
	 */
	public void notifyToAllOnlineUsers(AbstractPacket pact) {
		if(pact == null ) return;

		userId2Sessions.values().forEach( session -> session.sendPacket(pact));
	}

	public IoSession getSessionBy(String userId) {
		return this.userId2Sessions.get(userId);
	}

	public boolean registerSession(User user, IoSession session) {
		session.setUser(user);
		userId2Sessions.put(user.getUserId(), session);
		session2UserIds.put(session, user.getUserId());

		logger.info("[{}] registered...", user.getUserId());
		return true;
	}

	/**
	 *   注销用户通信渠道
	 */
	public void ungisterUserContext(Channel context, SessionCloseReason reason){
		if(context  == null){
			return;
		}
		IoSession session = ChannelUtils.getSessionBy(context);
		String userId = session2UserIds.remove(session);
		userId2Sessions.remove(userId);
		UserInfoService.removeOnlineUser(userId);
		if (session != null) {
			session.close(reason);
		}
	}

}
