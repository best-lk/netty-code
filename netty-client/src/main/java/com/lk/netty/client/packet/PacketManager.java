package com.lk.netty.client.packet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.lk.netty.client.exception.IllegalPacketException;

/**
 *  ���ݰ�������
 * 2019��3��8��
 * likai
 */
public enum PacketManager {
	INSTANCE;

	public void execPacket(AbstractPacket pact) {
		if(pact == null) return;
		try {
			Method m = pact.getClass().getMethod("execPacket");
			m.invoke(pact, null);
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

	public AbstractPacket createNewPacket(int packetType) {
		Class<? extends AbstractPacket> packetClass = PacketType.getPacketClassBy(packetType);
		if (packetClass == null) {
			throw new IllegalPacketException("����Ϊ"+packetType+"����Ϣ���岻����");
		}
		AbstractPacket packet = null;
		try {
			packet = (AbstractPacket)packetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalPacketException("����Ϊ"+packetType+"����Ϣʵ����ʧ��");
		}

		return packet;
	}
}
