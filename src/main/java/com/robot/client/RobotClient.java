package com.robot.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.robot.bean.SocketInfo;


public class RobotClient {
	
	final static Logger logger = Logger.getLogger(RobotClient.class);
	
	private Socket socket;
	private PrintStream ps;
	private SocketInfo inf;
	//private final Thread heartbeatThread;
	//private boolean tryToReconnect = true;
	//private long heartbeatDelayMillis = 5000;
	
	public RobotClient(final String server, final int port) {
		inf = new SocketInfo("online");
		try {
			connect(server,port);
			logger.info("Connected...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			inf.setState("offline");
			System.exit(0);
		}
        
	}
	
	public void closeCon() {
		try {
			socket.close();
			ps.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e + ": Problem with close");
			e.printStackTrace();
			inf.setState("offline");
		}
	}
	
	public void connect(String server, int port) throws InterruptedException {
		try {
            socket = new Socket(server, port);
            ps = new PrintStream(socket.getOutputStream());
            inf.setState("online");
        } catch (UnknownHostException e) {
            logger.error(e, e);
            inf.setState("offline");
            Thread.sleep(5000);
            connect(server, port);
        } catch (IOException e) {
            logger.error(e, e);
            inf.setState("offline");
            Thread.sleep(5000);
            connect(server, port);
        }
	}
	
	public void send(String s, float x, float y, float z) {
		
		if(s.equals("y")) {
			ps.println(s);
			ps.println(x);
			ps.println(y);
			ps.println(z);
		}else {
			ps.println("exit");
		}

	}
	
	public SocketInfo getSocketInfo() {
		return inf;
	}
}
