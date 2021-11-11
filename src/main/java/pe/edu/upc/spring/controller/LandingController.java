package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class LandingController {
	
	@RequestMapping("/landing")
	public String irPaginaInicio() {
		return "landing";
	}
	
}