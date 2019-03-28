package com.lk.netty.server.packet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.lk.netty.server.packet.req.*;
import com.lk.netty.server.packet.res.*;

/**
 *  自定义数据包类型
 * 2019年3月11日
 * likai
 */
public enum PacketType {
	/** 请求--链接心跳包 */
	ReqHeartBeat(1001, ReqHeartBeat.class),
	/** 推送--链接心跳包  */
	ResHeartBeat(1002, ResHeartBeat.class),

	/** 请求--用户登陆  */
	ReqUserLogin(2001, ReqUserLogin.class),
    /** 推送--用户登录  */
	RespUserLogin(2002, ResUserLogin.class),
	
	/** 请求--用户信息 */
	ReqUserInfo(3001, ReqUserInfo.class),
	/** 推送--用户信息 */
	ResUserInfo(3002, ResUserInfo.class),
	
	/**请求 --创建分组**/
	ReqCreateGroup(4001, ReqCreateGroup.class),
	/**推送 --创建分组**/
	ResCreateGroup(4002, ResCreateGroup.class),
	
	/**单人聊天**/
	ReqChatToSingle(5001, ReqChatToSingle.class),
	ResChatToSingle(5002, ResChatToSingle.class),
	
	/**群聊**/
	ReqChatToGroup(5003, ReqChatToGroup.class),
	ResChatToGroup(5004, ResChatToGroup.class),
	
	/**登出**/
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
				throw new IllegalStateException("packet type 协议类型重复"+type);
			}
			Class<?> packet = p.getPacketClass();
			if (packets.contains(packet)) {
				throw new IllegalStateException("packet定义重复"+p);
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
