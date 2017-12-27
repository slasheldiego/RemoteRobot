package com.robot.client;

import com.robot.bean.SocketInfo;

public interface RobotClient {
	public void closeCon();
	public void connect(String server, int port);
	public void send(String s, float x, float y, float z);
	public SocketInfo getSocketInfo();
	public void setSocketInfo(String state);
	public boolean isSocketAlive(String hostName, int port);
}
