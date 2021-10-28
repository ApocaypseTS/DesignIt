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

import pe.edu.upc.spring.model.Area;
import pe.edu.upc.spring.service.IAreaService;

@Controller
@RequestMapping("/area")
public class AreaController {
	
	@Autowired
	private IAreaService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoAreas(Map<String, Object> model) {
		model.put("listaAreas", rService.listar());
		return "listArea"; 
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("area", new Area());
		return "area"; 
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Area objArea, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
			return "area";
		else {
			boolean flag = rService.grabar(objArea);
			if(flag)
				return "redirect:/area/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/area/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Area> objArea = rService.listarId(id);
		if(objArea == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/area/listar";
		}
		else {
			model.addAttribute("area", objArea);
			return "area";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaAreas", rService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaAreas", rService.listar());
		}
		return "listArea";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAreas", rService.listar());
		return "listArea";
	}
}
