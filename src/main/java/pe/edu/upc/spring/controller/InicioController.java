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
	@RequestMapping("/reserva")
	public String irPaginaReserva() {
		return "reserva"; //"landing" es una pagina del frontend
	}
	@RequestMapping("/pago")
	public String irPaginaPago() {
		return "pago"; //"landing" es una pagina del frontend
	}
	@RequestMapping("/historial")
	public String irPaginaReserva2() {
		return "historial"; //"landing" es una pagina del frontend
	}
}
