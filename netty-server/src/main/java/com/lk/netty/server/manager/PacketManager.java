package com.lk.netty.server.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.lk.netty.server.exception.IllegalPacketException;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;

/**
 *  ��������
 * 2019��3��11��
 * likai
 */
public enum PacketManager {
	
	INSTANCE;

	public void execPacket(IoSession session, AbstractPacket pact){
		if(pact == null) return;
		try {
			Method m = pact.getClass().getMethod("execPacket", IoSession.class);
			m.invoke(pact, session);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public  AbstractPacket createNewPacket(int packetType){
		Class<? extends AbstractPacket> packetClass = PacketType.getPacketClassBy(packetType);
		if(packetClass == null){
			throw new IllegalPacketException("����Ϊ"+packetType+"�İ����岻����");
		}
		AbstractPacket packet = null;
		try {
			packet = (AbstractPacket)packetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalPacketException("����Ϊ"+packetType+"�İ�ʵ����ʧ��");
		}

		return packet;
	}
}
