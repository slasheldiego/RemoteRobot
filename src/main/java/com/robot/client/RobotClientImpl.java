package com.robot.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import com.robot.bean.SocketInfo;

//@Component
public class RobotClientImpl implements RobotClient {
	
	final static Logger logger = Logger.getLogger(RobotClientImpl.class);
	
	private Socket socket;
	private PrintStream ps;
	private SocketInfo inf;
	//private final Thread heartbeatThread;
	//private boolean tryToReconnect = true;
	//private long heartbeatDelayMillis = 5000;
	
	public void closeCon() {
		try {
			socket.close();
			ps.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e + ": Problem with close");
			e.printStackTrace();
			inf.setState("Offline");
		}
	}
	
	public void connect(String server, int port) {
		inf = new SocketInfo("Online");
        try {
			socket = new Socket(server, port);
			ps = new PrintStream(socket.getOutputStream());
	        inf.setState("Online");
	        logger.info("Socket is connected");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			inf.setState("Offline");
			logger.error("UnknownHostException connect: offline");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			inf.setState("Offline");
			logger.error("IOException connect: offline");
		}
        
        
	}
	
	public void send(String s, float x, float y, float z) {
		if( ps != null ) {
			if(s.equals("y")) {
				ps.println(s);
				ps.println(x);
				ps.println(y);
				ps.println(z);
			}else {
				ps.println("exit");
			}
		}else {
			inf.setState("Offline");
			logger.info("ps: null - offline");
		}

	}
	
	public SocketInfo getSocketInfo() {
		SocketInfo info = this.inf;
		System.out.println("Aqui es el getSocketInfo...");
		return info;
	}
	
	public void setSocketInfo(String state) {
		this.inf.setState(state);
	}
	
	public boolean isSocketAlive(String hostName, int port) {
		boolean isAlive = false;
 
		// Creates a socket address from a hostname and a port number
		SocketAddress socketAddress = new InetSocketAddress(hostName, port);
		Socket socket = new Socket();
 
		// Timeout required - it's in milliseconds
		int timeout = 2000;
 
		logger.info("hostName: " + hostName + ", port: " + port);
		try {
			socket.connect(socketAddress, timeout);
			socket.close();
			isAlive = true;
 
		} catch (SocketTimeoutException exception) {
			logger.error("SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage());
			inf.setState("Offline");
		} catch (IOException exception) {
			logger.error(
					"IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
			inf.setState("Offline");
		}
		return isAlive;
	}
}
