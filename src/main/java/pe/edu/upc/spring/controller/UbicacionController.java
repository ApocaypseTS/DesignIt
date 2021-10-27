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

import pe.edu.upc.spring.model.Ubicacion;
import pe.edu.upc.spring.service.IUbicacionService;

@Controller
@RequestMapping("/ubicacion")
public class UbicacionController {
	
	@Autowired
	private IUbicacionService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; //"bienvenido" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoUbicaciones(Map<String, Object> model) {
		model.put("listaUbicaciones", rService.listar());
		return "listUbicaciones"; //"listUbicaciones" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("ubicacion", new Ubicacion());
		return "ubicacion"; //"ubicacion" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Ubicacion objUbicacion, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
			return "ubicacion";
		else {
			boolean flag = rService.grabar(objUbicacion);
			if(flag)
				return "redirect:/ubicacion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/ubicacion/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Ubicacion> objUbicacion = rService.listarId(id);
		if(objUbicacion == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/ubicacion/listar";
		}
		else {
			model.addAttribute("ubicacion", objUbicacion);
			return "ubicacion";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaUbicaciones", rService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaUbicaciones", rService.listar());
		}
		return "listUbicacion";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUbicaciones", rService.listar());
		return "listUbicacion";
	}
}
