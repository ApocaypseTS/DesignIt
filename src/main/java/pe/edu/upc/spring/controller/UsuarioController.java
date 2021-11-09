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

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.model.Rol;

import pe.edu.upc.spring.service.IUsuarioService;
import pe.edu.upc.spring.service.IRolService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IRolService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "landing"; 
	}
	
	@RequestMapping("/login")
	public String irPaginaLogueo() {
		return "login"; //"login" es una pagina del frontend
	}
	
<<<<<<< HEAD
=======


>>>>>>> main
	@RequestMapping("/trabajador")
	public String irPaginaTrabajador() {
		return "trabajador"; //"trabajador" es una pagina del frontend
	}


	@RequestMapping("/")
	public String irPaginaListadoUsuarios(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "listUsuario";
	}
	
	/*este es de prueba*/
	@RequestMapping("/registro")
	public String irPaginaregistro() {
		return "registrar"; //"trabajador" es una pagina del frontend
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaRoles", rService.listar());
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("rol", new Rol());

		return "registrar"; //"registrar" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Usuario objUsuario, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaRoles", rService.listar());
			return "usuario";
		}
		else {
			boolean flag = uService.grabar(objUsuario);
			if(flag)
				return "redirect:/usuario/login";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/usuario/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/registroPrestamista")
	public String irPaginaRegistroPrestamista(Model model) {
		
		model.addAttribute("listaRoles", rService.listar());
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("rol", new Rol());

		return "registrar_trabajador"; //"registrar_trabajador" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrarPrestamista")
	public String registrarPrestamista(@ModelAttribute Usuario objUsuario, BindingResult binRes, Model model) throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaRoles", rService.listar());
			return "usuario";
		}
		else {
			boolean flag = uService.grabar(objUsuario);
			if(flag)
				return "redirect:/usuario/trabajador";
			else {
				model.addAttribute("mensaje", "Ocurrio un accidente, LUZ ROJA");
				return "redirect:/usuario/registroPrestamista";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Usuario> objUsuario = uService.listarId(id);
		if(objUsuario == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un roche, LUZ ROJA");
			return "redirect:/usuario/listar";
		}
		else {
			model.addAttribute("usuario", objUsuario);
			return "registrar_trabajador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				uService.eliminar(id);
				model.put("listaUsuarios", uService.listar());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaUsuarios", uService.listar());
		}
		return "trabajador";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "trabajador";
	}
}
