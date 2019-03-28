package com.lk.netty.server.packet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.lk.netty.server.packet.req.*;
import com.lk.netty.server.packet.res.*;

/**
 *  �Զ������ݰ�����
 * 2019��3��11��
 * likai
 */
public enum PacketType {
	/** ����--���������� */
	ReqHeartBeat(1001, ReqHeartBeat.class),
	/** ����--����������  */
	ResHeartBeat(1002, ResHeartBeat.class),

	/** ����--�û���½  */
	ReqUserLogin(2001, ReqUserLogin.class),
    /** ����--�û���¼  */
	RespUserLogin(2002, ResUserLogin.class),
	
	/** ����--�û���Ϣ */
	ReqUserInfo(3001, ReqUserInfo.class),
	/** ����--�û���Ϣ */
	ResUserInfo(3002, ResUserInfo.class),
	
	/**���� --��������**/
	ReqCreateGroup(4001, ReqCreateGroup.class),
	/**���� --��������**/
	ResCreateGroup(4002, ResCreateGroup.class),
	
	/**��������**/
	ReqChatToSingle(5001, ReqChatToSingle.class),
	ResChatToSingle(5002, ResChatToSingle.class),
	
	/**Ⱥ��**/
	ReqChatToGroup(5003, ReqChatToGroup.class),
	ResChatToGroup(5004, ResChatToGroup.class),
	
	/**�ǳ�**/
	ReqUserLogout(6001, ReqUserLogout.class),
	ResUserLogout(6002, ResUserLogout.class);

	private int type;
	private Class<? extends AbstractPacket> packetClass;
	private static Map<Integer,Class<? extends AbstractPacket>> PACKET_CLASS_MAP = new HashMap<>();

	public static void initPackets() {
		Set<Integer> typeSet = new HashSet<>();
		Set<Class<?>> packets = new HashSet<>();
		for(PacketType p:PacketType.values()){
			int type = p.getType();
			if(typeSet.contains(type)){
				throw new IllegalStateException("packet type Э�������ظ�"+type);
			}
			Class<?> packet = p.getPacketClass();
			if (packets.contains(packet)) {
				throw new IllegalStateException("packet�����ظ�"+p);
			}
			PACKET_CLASS_MAP.put(type,p.getPacketClass());
			typeSet.add(type);
			packets.add(packet);
		}
	}
	
	private PacketType() {
	}
	
	PacketType(int type,Class<? extends AbstractPacket> packetClass){
		this.setType(type);
		this.packetClass = packetClass;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Class<? extends AbstractPacket> getPacketClass() {
		return packetClass;
	}

	public void setPacketClass(Class<? extends AbstractPacket> packetClass) {
		this.packetClass = packetClass;
	}


	public static  Class<? extends AbstractPacket> getPacketClassBy(int packetType){
		return PACKET_CLASS_MAP.get(packetType);
	}

	public static void main(String[] args) {
		for(PacketType p:PacketType.values()){
			System.err.println(p.getPacketClass().getSimpleName());
		}
	}
}
