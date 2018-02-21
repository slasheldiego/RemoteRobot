package com.robot.client;

import java.util.ArrayList;

import com.robot.bean.SocketInfo;

public interface RobotClient {
	public void closeCon();
	public void connect();
	public void send(float x, float y, float z);
	public ArrayList<String> readMessage() ;
	public SocketInfo getSocketInfo();
	public void setSocketInfo(String state);
	public void setSocketAction(String action);
	public void setSocketX(float x);
	public void setSocketY(float y);
	public void setSocketZ(float z);
	public void setRead_message_status(boolean read_message_status);
}
