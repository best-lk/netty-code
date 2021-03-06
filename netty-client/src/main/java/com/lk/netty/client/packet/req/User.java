package com.lk.netty.client.packet.req;

/**
 * 用户信息实体
 * 2019年3月7日
 * likai
 */
public class User {
	
	private String userId;
	
	private String userName;
	
	private String password;

	public User(String userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	
	public User() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
