package com.robot.bean;

public class SocketInfo {
	private String state = "Online";
	private String action = "Connect";
	private float x_coor = 0;
	private float y_coor = 0;
	private float z_coor = 0;
	
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

	public float getX_coor() {
		return x_coor;
	}

	public void setX_coor(float x_coor) {
		this.x_coor = x_coor;
	}

	public float getY_coor() {
		return y_coor;
	}

	public void setY_coor(float y_coor) {
		this.y_coor = y_coor;
	}

	public float getZ_coor() {
		return z_coor;
	}

	public void setZ_coor(float z_coor) {
		this.z_coor = z_coor;
	}
}
