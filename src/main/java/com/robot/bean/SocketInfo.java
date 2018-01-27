package com.robot.bean;

public class SocketInfo {
	private String state = "Online";
	private String action = "Connect";
	
	public SocketInfo() {}
	
	public SocketInfo(String state, String action) {
		this.state = state;
		this.action = action;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
