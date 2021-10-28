package pe.edu.upc.spring.controller;

//import java.util.List;
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

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Area;

import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.IAreaService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
	
	@Autowired
	private IServicioService sService;
	
	@Autowired
	private IAreaService aService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoServicios(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "listServicio"; 
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaAreas", aService.listar());
		
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("area", new Area());

		return "servicio";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Servicio objServicio, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaAreas", aService.listar());
			return "servicio";
		}
		else {
			boolean flag = sService.grabar(objServicio);
			if(flag)
				return "redirect:/servicio/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/servicio/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Servicio> objServicio = sService.listarId(id);
		if(objServicio == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/servicio/listar";
		}
		else {
			model.addAttribute("listaAreas", aService.listar());
			if(objServicio.isPresent())
				objServicio.ifPresent(o -> model.addAttribute("servicio",o));
			
			return "servicio";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				sService.eliminar(id);
				model.put("listaServicios", sService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaServicios", sService.listar());
		}
		return "listServicio";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaServicios", sService.listar());
		return "listServicio";
	}
	
	/*@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Servicio servicio) throws ParseException 
	{
		sService.listarId(servicio.getIdServicio());
		return "listServicio";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) throws ParseException 
	{
		model.addAttribute("servicio", new Servicio());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Servicio servicio) throws ParseException 
	{
		List<Servicio> listaServicios;
		servicio.setNameServicio(servicio.getNameServicio());
		listaServicios = sService.buscarNombre(servicio.getNameServicio());
		
		if(listaServicios.isEmpty()) {
			listaServicios = sService.buscarArea(servicio.getNameServicio());
		}
		
		if(listaServicios.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaServicios", listaServicios);
		return "buscar";
	}*/
}
