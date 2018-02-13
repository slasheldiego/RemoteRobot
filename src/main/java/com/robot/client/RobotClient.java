package com.robot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.robot.bean.SocketInfo;

@Component
public class RobotClient {
	
	final static Logger logger = Logger.getLogger(RobotClient.class);
	
	private Socket socket;
	private PrintStream ps;
	private SocketInfo inf;
	private BufferedReader br;
	private boolean r = false;
	//private final Thread heartbeatThread;
	//private boolean tryToReconnect = true;
	//private long heartbeatDelayMillis = 5000;
	
	public RobotClient(){
		//connect("localhost", 55000);
		inf = new SocketInfo("Offline","Connect");
	}
	
	public void closeCon() {
		try {
			socket.close();
			ps.close();
			inf.setState("Offline");
			inf.setAction("Connect");
			r = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e + ": Problem with close");
			e.printStackTrace();
			inf.setState("Offline");
			inf.setAction("Connect");
		}
	}
	
	public void connect(String server, int port) {
        try {
			socket = new Socket(server, port);
			ps = new PrintStream(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        inf.setState("Online");
	        logger.info("Socket is connected");
	        ps.write("y".getBytes("UTF-8"));
            ps.flush();
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
	
	public void send(String s, float x, float y, float z) throws UnsupportedEncodingException, IOException, InterruptedException {
		if( ps != null ) {
			if(s.equals("y")) {
				//ps.println(s);
				/*ps.println(x);
				ps.println(y);
				ps.println(z);*/
				/*ps.write(s.getBytes("UTF-8"));
	            	ps.flush();
	            	Thread.sleep(1000);*/
				/*BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = br.readLine();
				System.out.println(line);*/
	            	ps.write(String.valueOf(x).getBytes("UTF-8"));
	            	ps.flush();
	            	Thread.sleep(1000);
	            	ps.write(String.valueOf(y).getBytes("UTF-8"));
	            	ps.flush();
	            	Thread.sleep(1000);
	            	ps.write(String.valueOf(z).getBytes("UTF-8"));
	            	ps.flush();
	            	Thread.sleep(1000);
	            	ps.write(s.getBytes("UTF-8"));
	            	//ps.println("");
	            	ps.flush();
	            	r = true;
			}else {
				ps.println("exit");
			}
		}else {
			inf.setState("Offline");
			logger.info("ps: null - offline");
		}

	}
	
	public ArrayList<String> readMessage() {
		ArrayList<String> list = new ArrayList<String>();
		String line = "";
		double x_len = 0;
		double y_len = 0;
		double z_len = 0;
		double x = 0;
		double y = 0;
		double z = 0;
		char c;
		try {
			System.out.println("AQUI???1");
			if(br != null && r) {
				while((c = (char) br.read()) != 'x') {
		            line = line + c;
				}
				System.out.println(line);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				x_len = Double.parseDouble(line);
				System.out.println("x_len:"+x_len);
				
				line = "";
				while((c = (char) br.read()) != 'y') {
		            line = line + c;
				}
				System.out.println("x:"+line);
				x = Double.parseDouble(line);
				if(x < 1) { x = Math.round(x); }
				list.add(""+x);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				y_len = Double.parseDouble(line);
				System.out.println("y_len:"+y_len);
				
				line = "";
				while((c = (char) br.read()) != 'z') {
		            line = line + c;
				}
				System.out.println("y:"+line);
				y = Double.parseDouble(line);
				if(y < 1) { y = Math.round(x); }
				list.add(""+y);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				z_len = Double.parseDouble(line);
				System.out.println("z_len:"+z_len);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				System.out.println("z:"+line);
				z = Double.parseDouble(line);
				if(z < 1) { z = Math.round(z); }
				list.add(""+z);
			}else {
				list = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			inf.setState("Offline");
			logger.info("ps: null - offline");
		}
		return list;
	}
	
	public SocketInfo getSocketInfo() {
		SocketInfo info = this.inf;
		return info;
	}
	
	public void setSocketInfo(String state) {
		this.inf.setState(state);
	}
	
	public void setSocketAction(String action) {
		this.inf.setAction(action);
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
