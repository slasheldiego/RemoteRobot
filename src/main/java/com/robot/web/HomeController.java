package com.robot.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
	public String home(Model model,HttpServletRequest request) {
		model.addAttribute("Move",new RobotMovement());
		model.addAttribute("info", client.getSocketInfo());
		model.addAttribute("localip",request.getRemoteAddr());
		return "home";
	}
	
	@RequestMapping(value = "move",method=RequestMethod.POST)
	public String move(Model model,
			@ModelAttribute("Move") RobotMovement move) throws UnsupportedEncodingException, IOException, InterruptedException {
			if( client.getSocketInfo().getState().equals("Online")) {
				logger.info("move: Socket is connected...");
				model.addAttribute("info",client.getSocketInfo());
				sendCoordinates(move);
		    		return "redirect:/";
			}else {
				logger.error("move: Socket is not connected...");
				client.setSocketInfo("Offline");
				SocketInfo info = new SocketInfo("Offline","Connect");
				model.addAttribute("info",info);
				initClient(server,port);
				return "redirect:/";
			}
		
	}
	
	@RequestMapping(value = "connect",method=RequestMethod.GET)
	public String connect(Model model,
			@ModelAttribute("Move") RobotMovement move) {
		logger.info("Connect: Try to connect Socket ...");
		
		initClient(server,port);
		if( client.getSocketInfo().getState().equals("Online") ) {
			model.addAttribute("info",client.getSocketInfo());
			client.setSocketAction("Disconnect");
			logger.info("RobotClient: not null");
		}else {
			client.setSocketInfo("Offline");
			client.setSocketAction("Connect");
			model.addAttribute("info",client.getSocketInfo());
			logger.error("RobotClient or Socket: null - Offline");
		}

		model.addAttribute("info",client.getSocketInfo());
		
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "disconnect",method=RequestMethod.GET)
	public String disconnect(Model model,
			@ModelAttribute("Move") RobotMovement move) {
		logger.info("Disconnect: Try to disconnect Socket ...");
		
		client.closeCon();

		model.addAttribute("info",client.getSocketInfo());
		
		return "redirect:/";
		
	}
	
	public void initClient(String server, int port) {
			client.connect(server,port);
	}
	
	public void sendCoordinates(RobotMovement move) throws UnsupportedEncodingException, IOException, InterruptedException {
		client.send(move.getS(), move.getX(), move.getY(), move.getZ());
	}
	
}
