package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	@RequestMapping("/bienvenido2")
	public String irPaginaBienvenid() {
		return "ingresa";
	}
	@RequestMapping("/bienvenido3")
	public String irPaginaBienvenid3() {
		return "/redirect:planner";
	}
	@RequestMapping("/bienvenido1")
	public String irP() {
		return "seleccionar";
	}
	
}