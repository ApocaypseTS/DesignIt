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

import pe.edu.upc.spring.model.Rol;
import pe.edu.upc.spring.service.IRolService;

@Controller
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private IRolService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoRoles(Map<String, Object> model) {
		model.put("listaRoles", rService.listar());
		return "listRoles";
	} 
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("rol", new Rol());
		return "rol";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Rol objRol, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
			return "rol";
		else {
			boolean flag = rService.grabar(objRol);
			if(flag)
				return "redirect:/rol/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/rol/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Rol> objRol = rService.listarId(id);
		if(objRol == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/rol/listar";
		}
		else {
			model.addAttribute("rol", objRol);
			return "rol";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaRoles", rService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaRoles", rService.listar());
		}
		return "listRol";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRoles", rService.listar());
		return "listRol";
	}
}
