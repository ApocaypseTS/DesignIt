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

import pe.edu.upc.spring.model.Orden;
import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Usuario;

import pe.edu.upc.spring.service.IOrdenService;
import pe.edu.upc.spring.service.IServicioService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/orden")
public class OrdenController {
	
	@Autowired
	private IOrdenService oService;
	
	@Autowired
	private IServicioService sService;
	
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing"; //"landing" es una pagina del frontend
	}
	
	@RequestMapping("/pago")
	public String irPaginaPago() {
		return "pago"; //"pago" es una pagina del frontend
	}
	
	@RequestMapping("/")
	public String irPaginaListadoOrdenes(Map<String, Object> model) {
		model.put("listaOrdenes", oService.listar());
		return "listOrden"; //"listOrden" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaServicios", sService.listar());
		model.addAttribute("listaUsuarios", uService.listar());
		
		model.addAttribute("orden", new Orden());
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("usuario", new Usuario());

		return "orden"; //"orden" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Orden objOrden, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaServicios", sService.listar());
			model.addAttribute("listaUsuarios", uService.listar());
			return "orden";
		}
		else {
			boolean flag = oService.grabar(objOrden);
			if(flag)
				return "redirect:/orden/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/orden/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Orden> objOrden = oService.listarId(id);
		if(objOrden == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/orden/listar";
		}
		else {
			model.addAttribute("listaServicios", sService.listar());
			model.addAttribute("listaUsuarios", uService.listar());
			if(objOrden.isPresent())
				objOrden.ifPresent(o -> model.addAttribute("orden",o));
			
			return "orden";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				oService.eliminar(id);
				model.put("listaOrdenes", oService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaOrdenes", oService.listar());
		}
		return "listOrden";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaOrdenes", oService.listar());
		return "listOrden";
	}
	
	/*@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Pet pet) throws ParseException 
	{
		pService.listarId(pet.getIdPet());
		return "listPet";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) throws ParseException 
	{
		model.addAttribute("pet", new Pet());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Pet pet) throws ParseException 
	{
		List<Pet> listaMascotas;
		pet.setNamePet(pet.getNamePet());
		listaMascotas = pService.buscarNombre(pet.getNamePet());
		
		if(listaMascotas.isEmpty()) {
			listaMascotas = pService.buscarPropietario(pet.getNamePet());
		}
		if(listaMascotas.isEmpty()) {
			listaMascotas = pService.buscarRaza(pet.getNamePet());
		}
		if(listaMascotas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaMascotas", listaMascotas);
		return "buscar";
	}*/
}
