package com.robot.client;

import java.util.ArrayList;

import com.robot.bean.SocketInfo;

public interface RobotClient {
	public void closeCon();
	public void connect(String server, int port);
	public void send(String s, float x, float y, float z);
	public ArrayList<String> readMessage() ;
	public SocketInfo getSocketInfo();
	public void setSocketInfo(String state);
	public void setSocketAction(String action);
}
