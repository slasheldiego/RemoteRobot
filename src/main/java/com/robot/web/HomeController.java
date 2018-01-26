package com.robot.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
@RequestMapping("/")
public class HomeController {
	
	final static Logger logger = Logger.getLogger(HomeController.class);
	
	private RobotClient client;
	private final String server = "localhost";
	private final int port = 55000;
	
	@Autowired
	public HomeController(RobotClient client) {
		this.client = client;
	}
	
	@RequestMapping(method=GET)
	public String home(Model model) {
		model.addAttribute("Move",new RobotMovement());

		//initClient(server,port);
		if( client.getSocketInfo().getState().equals("Online") ) {
			model.addAttribute("info",client.getSocketInfo());
			logger.info("RobotClient: not null");
		}else {
			SocketInfo info = new SocketInfo("Offline");
			model.addAttribute("info",info);
			logger.error("RobotClient or Socket: null - Offline");
			initClient(server,port);
		}
		return "home";
	}
	
	@RequestMapping(value = "move",method=RequestMethod.POST)
	public String move(Model model,
			@ModelAttribute("Move") RobotMovement move) throws UnsupportedEncodingException, IOException, InterruptedException {
			if( client.getSocketInfo().getState().equals("Online")) {
				logger.info("move: Socket is connected...");
				model.addAttribute("info",client.getSocketInfo());
				sendCoordinates(move);
		    		return "home";
			}else {
				logger.error("move: Socket is not connected...");
				client.setSocketInfo("Offline");
				SocketInfo info = new SocketInfo("Offline");
				model.addAttribute("info",info);
				initClient(server,port);
				return "redirect:/";
			}
		
	}
	
	public void initClient(String server, int port) {
			client.connect(server,port);
	}
	
	public void sendCoordinates(RobotMovement move) throws UnsupportedEncodingException, IOException, InterruptedException {
		client.send(move.getS(), move.getX(), move.getY(), move.getZ());
	}
	
}
