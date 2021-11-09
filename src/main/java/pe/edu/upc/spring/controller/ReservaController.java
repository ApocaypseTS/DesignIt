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

import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.model.Ubicacion;
import pe.edu.upc.spring.model.Orden;

import pe.edu.upc.spring.service.IReservaService;
import pe.edu.upc.spring.service.IUsuarioService;
import pe.edu.upc.spring.service.IUbicacionService;
import pe.edu.upc.spring.service.IOrdenService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	private IReservaService rService;
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IUbicacionService ubService;
	
	@Autowired
	private IOrdenService oService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing"; 
	}
	
	
	@RequestMapping("/registro")
	public String irPaginaReserva8() {
		return "selecionar_reserva"; //"landing" es una pagina del frontend
	}
	
	
	
	@RequestMapping("/listareserva")
	public String irPaginaReserva() {
		return "reserva"; 
	}
	
	@RequestMapping("/")
	public String irPaginaListadoReservas(Map<String, Object> model) {
		model.put("listaReservas", rService.listar());
		return "listReserva";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("listaUbicaciones", ubService.listar());
		model.addAttribute("listaOrdenes", oService.listar());
		
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("ubicacion", new Ubicacion());
		model.addAttribute("orden", new Orden());

		return "reserva"; 
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Reserva objReserva, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaUbicaciones", ubService.listar());
			model.addAttribute("listaOrdenes", oService.listar());
			return "reserva";
		}
		else {
			boolean flag = rService.grabar(objReserva);
			if(flag)
				return "redirect:/reserva/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/reserva/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Reserva> objReserva = rService.listarId(id);
		if(objReserva == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/reserva/listar";
		}
		else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaUbicaciones", ubService.listar());
			model.addAttribute("listaOrdenes", oService.listar());
			if(objReserva.isPresent())
				objReserva.ifPresent(o -> model.addAttribute("reserva",o));
			
			return "reserva";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaReservas", rService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaReservas", rService.listar());
		}
		return "listReserva";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaReservas", rService.listar());
		return "listReserva";
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
