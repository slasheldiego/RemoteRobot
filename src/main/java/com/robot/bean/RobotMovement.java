package com.robot.bean;

public class RobotMovement {
	private String s;
	private float x;
	private float y;
	private float z;
	
	public RobotMovement() {}
	
	public RobotMovement(String s, float x, float y, float z) {
		this.s = s;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
