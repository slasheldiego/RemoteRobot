package com.robot.bean;

public class SocketInfo {
	private String state = "Online";
	
	public SocketInfo() {}
	
	public SocketInfo(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
