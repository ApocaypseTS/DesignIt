package pe.edu.upc.spring.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.ParseException;


import pe.edu.upc.spring.model.EventPlanner;

import pe.edu.upc.spring.service.IEventPlannerService;
import pe.edu.upc.spring.service.IPlannerService;
import pe.edu.upc.spring.service.IEventService;

@Controller
@RequestMapping("/eventPlanner")
public class EventPlannerController {

	@Autowired
	private IEventPlannerService evplService;
	@Autowired
	private IEventService eService;
	@Autowired
	private IPlannerService pService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoPlanneres(Map<String, Object> model) {
		model.put("listaEventPlanner", evplService.listar());
		return "eventplanner/listEventPlanner";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("eventplanner", new EventPlanner());
		model.addAttribute("listaPlanneres", pService.listar());
		model.addAttribute("listaEvents", eService.listar());

					return "eventplanner/eventplanner";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute EventPlanner objEventPlanner, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
				return "eventplanner/eventplanner";
		}else {
			boolean flag = evplService.insertar(objEventPlanner);
			if (flag)
				return "redirect:/eventplanner/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/eventplanner/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		EventPlanner objEventPlanner = evplService.listarId(id).get();
		if (objEventPlanner == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/eventplanner/listar";
		} else {
			model.addAttribute("listaEvents", eService.listar());
			model.addAttribute("listaPlanneres", pService.listar());
			model.addAttribute("eventplanner", objEventPlanner);

			return "eventplanner/eventplanner";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				evplService.eliminar(id);
				model.put("listaEventPlanner", evplService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaEventPlanner", evplService.listar());
		}
		return "eventplanner/listEventPlanner";
		}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEventPlanner", evplService.listar());
		return "eventoprestador/listEventoPrestador";
	}
}