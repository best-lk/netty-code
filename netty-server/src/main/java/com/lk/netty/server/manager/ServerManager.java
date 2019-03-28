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

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * ���������
 * 2019��3��8��
 * likai
 */
public enum ServerManager {
	
	INSTANCE;
	 
	private Logger logger = LoggerFactory.getLogger(ServerManager.class);
 
	/** ����ͨ�������Ļ�����Ӧ�ĵ�¼�û�����Ҫ���ڷ��� */ 
	private Map<IoSession, Long> session2UserIds  = new ConcurrentHashMap<>();
 
	/** �����û�id���Ӧ�ĻỰ */
	private ConcurrentMap<String, IoSession> userId2Sessions = new ConcurrentHashMap<>();
 
 
	public void sendPacketTo(AbstractPacket pact,Long userId){
		if(pact == null || userId <= 0) return;
 
		IoSession session = userId2Sessions.get(userId);
		if (session != null) {
			session.sendPacket(pact);
		}
	}
 
	/**
	 *  �����������û��������ݰ�
	 */
	public void sendPacketToAllUsers(AbstractPacket pact){
		if(pact == null ) return;
 
		userId2Sessions.values().forEach( (session) -> session.sendPacket(pact));
	}
 
	/**
	 *  ��һ�����û��������ݰ�
	 */
	public void sendPacketTo(AbstractPacket pact,ChannelHandlerContext targetContext ){
		if(pact == null || targetContext == null) return;
		targetContext.writeAndFlush(pact);
	}
 
 
	public IoSession getSessionBy(long userId) {
		return this.userId2Sessions.get(userId);
	}
 
	public boolean registerSession(User user, IoSession session) {
 
		session.setUser(user);
		userId2Sessions.put(user.getUserId(), session);
 
		logger.info("[{}] registered...", user.getUserId());
 
		return true;
	}
 
	/**
	 *   ע���û�ͨ������
	 */
	public void ungisterUserContext(Channel context ){
		if(context  == null){
 
			return;
		}
		IoSession session = ChannelUtils.getSessionBy(context);
		Long userId = session2UserIds.remove(session);
		userId2Sessions.remove(userId);
		if (session != null) {
			session.close(SessionCloseReason.OVER_TIME);
		}
	}
}
