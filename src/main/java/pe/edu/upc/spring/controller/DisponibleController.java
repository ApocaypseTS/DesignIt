package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Disponible;
import pe.edu.upc.spring.model.Orden;
import pe.edu.upc.spring.service.IDisponibleService;
import pe.edu.upc.spring.service.IOrdenService;

@Controller
@RequestMapping("/disponible")
public class DisponibleController {
	
	@Autowired
	private IDisponibleService dService;
	
	@Autowired
	private IOrdenService oService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing"; //"landing" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoUsuarios(Map<String, Object> model) {
		model.put("listaDisponibles", dService.listar());
		return "listDisponible"; //"listDisponible" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaOrdenes", oService.listar());
		
		model.addAttribute("disponible", new Disponible());
		model.addAttribute("orden", new Orden());

		return "disponible"; //"disponible" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Disponible objDisponible, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaOrdenes", oService.listar());
			return "disponible";
		}
		else {
			boolean flag = dService.grabar(objDisponible);
			if(flag)
				return "redirect:/disponible/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/disponible/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Disponible> objDisponible = dService.listarId(id);
		if(objDisponible == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/disponible/listar";
		}
		else {
			model.addAttribute("listaOrdenes", oService.listar());
			if(objDisponible.isPresent())
				objDisponible.ifPresent(o -> model.addAttribute("disponible",o));
			
			return "disponible";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaDisponibles", dService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaDisponibles", dService.listar());
		}
		return "listDisponible";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDisponibles", dService.listar());
		return "listDisponible";
	}
	
}
