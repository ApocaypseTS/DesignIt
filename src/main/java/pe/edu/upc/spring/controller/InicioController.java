package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class InicioController {
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing"; //"landing" es una pagina del frontend
	}
	
	@RequestMapping("/index")
	public String irPaginaInicio() {
		return "index"; //"landing" es una pagina del frontend
	}
	
}
