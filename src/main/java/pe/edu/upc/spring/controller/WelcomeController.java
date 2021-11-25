package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing";
	}
	@RequestMapping("/bienvenido4")
	public String irPaginaBienvenida4() {
		return "bienvenido";
	}
	@RequestMapping("/ae")
	public String irPaginaBienvenida0() {
		return "login";
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
	@RequestMapping("/nosotros")
	public String irNosotros() {
		return "nosotros";
	}
	@RequestMapping("/error")
	public String irError() {
		return "error_403";
	}
	
}