package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	@RequestMapping("/landing")
	public String irPaginaBienve() {
		return "landing";
	}
	
	@RequestMapping("/error")
	public String irError() {
		return "error_403";
	}
	
}