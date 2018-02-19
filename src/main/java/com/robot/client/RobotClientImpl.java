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
import org.springframework.stereotype.Service;

import com.robot.bean.SocketInfo;

@Service
public class RobotClientImpl implements RobotClient {
	
	final static Logger logger = Logger.getLogger(RobotClientImpl.class);
	
	private Socket socket;
	private PrintStream ps;
	private SocketInfo inf;
	private BufferedReader br;
	private boolean read_message_status = false;
	//private final Thread heartbeatThread;
	//private boolean tryToReconnect = true;
	//private long heartbeatDelayMillis = 5000;
	
	public RobotClientImpl(){
		//connect("localhost", 55000);
		inf = new SocketInfo("Offline","Connect");
	}
	
	public void closeCon() {
		try {
			socket.close();
			ps.close();
			inf.setState("Offline");
			inf.setAction("Connect");
			read_message_status = false;
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
			logger.error("UnknownHostException connect: offline - " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			inf.setState("Offline");
			logger.error("IOException connect: offline - " + e.getMessage());
		}
        
        
	}
	
	public void send(String s, float x, float y, float z)  {
		
		try {
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
		            	read_message_status = true;
				}else {
					ps.println("exit");
				}
			}else {
				inf.setState("Offline");
				logger.info("ps: null - offline");
			}
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}catch(IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}catch(InterruptedException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
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
			if(br != null && read_message_status) {
				logger.info("Read message from Robot Server Init ...");
				while((c = (char) br.read()) != 'x') {
		            line = line + c;
				}
				logger.info(line);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				x_len = Double.parseDouble(line);
				logger.info("x_len:"+x_len);
				
				line = "";
				while((c = (char) br.read()) != 'y') {
		            line = line + c;
				}
				logger.info("Add to Coordinate List x:"+line);
				x = Double.parseDouble(line);
				if(x < 1) { x = Math.round(x); }
				list.add(""+x);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				y_len = Double.parseDouble(line);
				logger.info("y_len:"+y_len);
				
				line = "";
				while((c = (char) br.read()) != 'z') {
		            line = line + c;
				}
				logger.info("Add to Coordinate List y:"+line);
				y = Double.parseDouble(line);
				if(y < 1) { y = Math.round(x); }
				list.add(""+y);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				z_len = Double.parseDouble(line);
				logger.info("z_len:"+z_len);
				
				line = "";
				while((c = (char) br.read()) != '|') {
		            line = line + c;
				}
				logger.info("Add to Coordinate List z:"+line);
				z = Double.parseDouble(line);
				if(z < 1) { z = Math.round(z); }
				list.add(""+z);
			}else {
				list = null;
				read_message_status = false;
				logger.warn("BufferedReader null or Sent Corrdinates Failure ...");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			inf.setState("Offline");
			logger.error("ps: null - offline - " + e.getMessage());
			list = null;
			setSocketAction("Connect");
			read_message_status = false;
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
