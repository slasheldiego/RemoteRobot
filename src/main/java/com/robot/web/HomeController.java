package com.robot.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.robot.bean.RobotMovement;
import com.robot.client.RobotClient;


@Controller
@RequestMapping("/")
public class HomeController {
	
	private RobotClient client;
	Thread hilo;
	
	@RequestMapping(method=GET)
	public String home(Model model) {
		model.addAttribute("Move",new RobotMovement());
		
		hilo = new Thread() {
			public void run(){
				initClient();
			}
		};
		hilo.run();
		model.addAttribute("info",client.getSocketInfo());
		return "home";
	}
	
	@RequestMapping(value = "move",method=RequestMethod.POST)
	public String move(
			@ModelAttribute("Move") RobotMovement move) {
		sendCoordinates(move);
	    return "home";
		
	}
	
	private void initClient() {
		client = new RobotClient("localhost",25557);
	}
	
	public void sendCoordinates(RobotMovement move) {
		client.send(move.getS(), move.getX(), move.getY(), move.getZ());
	}
}
