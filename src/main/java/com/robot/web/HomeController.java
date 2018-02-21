package com.robot.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.robot.bean.RobotMovement;
import com.robot.bean.SocketInfo;
import com.robot.client.RobotClient;


@Controller
@RequestMapping({"/","/home"})
public class HomeController {
	
	final static Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private RobotClient robotClient;
	
	
	@RequestMapping(method=GET)
	public String home(Model model,HttpServletRequest request) {
		model.addAttribute("Move",new RobotMovement());
		model.addAttribute("localip",request.getRemoteAddr());
		model.addAttribute("info", robotClient.getSocketInfo());
		return "home";
	}
	
	@RequestMapping(value = "move",method=RequestMethod.POST)
	public String move(Model model,
			@ModelAttribute("Move") RobotMovement move) {
			if( robotClient.getSocketInfo().getState().equals("Online") ) {
				logger.info("move: Socket is connected...");
				model.addAttribute("info",robotClient.getSocketInfo());
				try {
					sendCoordinates(move);
					robotClient.setRead_message_status(true);
					ArrayList<String> list;
					if(( list = robotClient.readMessage() ) != null ) {
						robotClient.setSocketX(Float.parseFloat(list.get(0)));
						robotClient.setSocketY(Float.parseFloat(list.get(1)));
						robotClient.setSocketZ(Float.parseFloat(list.get(2)));
					}else {
						robotClient.setSocketX(-1);
						robotClient.setSocketY(-1);
						robotClient.setSocketZ(-1);
					}
					robotClient.setRead_message_status(false);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				}
		    		return "redirect:/";
			}else {
				logger.error("move: Socket is not connected...");
				robotClient.setSocketInfo("Offline");
				SocketInfo info = new SocketInfo("Offline","Connect");
				model.addAttribute("info",info);
				initClient();
				return "redirect:/";
			}
		
	}
	
	@RequestMapping(value = "connect",method=RequestMethod.GET)
	public String connect(Model model,
			@ModelAttribute("Move") RobotMovement move) {
		logger.info("Connect: Try to connect Socket ...");
		
		initClient();
		if( robotClient.getSocketInfo().getState().equals("Online") ) {
			model.addAttribute("info",robotClient.getSocketInfo());
			robotClient.setSocketAction("Disconnect");
			logger.info("RobotClient: not null");
			robotClient.setRead_message_status(true);
			ArrayList<String> list;
			if(( list = robotClient.readMessage() ) != null ) {
				robotClient.setSocketX(Float.parseFloat(list.get(0)));
				robotClient.setSocketY(Float.parseFloat(list.get(1)));
				robotClient.setSocketZ(Float.parseFloat(list.get(2)));
			}else {
				robotClient.setSocketX(-1);
				robotClient.setSocketY(-1);
				robotClient.setSocketZ(-1);
			}
			robotClient.setRead_message_status(false);
		}else {
			robotClient.setSocketInfo("Offline");
			robotClient.setSocketAction("Connect");
			model.addAttribute("info",robotClient.getSocketInfo());
			logger.error("RobotClient or Socket: null - Offline");
		}

		model.addAttribute("info",robotClient.getSocketInfo());
		
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "disconnect",method=RequestMethod.GET)
	public String disconnect(Model model,
			@ModelAttribute("Move") RobotMovement move) {
		logger.info("Disconnect: Try to disconnect Socket ...");
		
		robotClient.closeCon();

		model.addAttribute("info",robotClient.getSocketInfo());
		
		return "redirect:/";
		
	}
	
	public void initClient() {
			robotClient.connect();
	}
	
	public void sendCoordinates(RobotMovement move) throws UnsupportedEncodingException, IOException, InterruptedException {
		robotClient.send(move.getX(), move.getY(), move.getZ());
	}
	
}
