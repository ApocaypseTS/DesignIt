package pe.edu.upc.spring.controller;

import java.util.ArrayList;
import java.util.List;
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
import java.text.ParseException;

import pe.edu.upc.spring.model.Event;
import pe.edu.upc.spring.model.EventPlanner;

import pe.edu.upc.spring.model.Request;
import pe.edu.upc.spring.model.Planner;

import pe.edu.upc.spring.service.IRequestService;
import pe.edu.upc.spring.service.IEventPlannerService;

@Controller
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private IRequestService rService;
	@Autowired
	private IEventPlannerService evplService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoPlanneres(Map<String, Object> model) {
		model.put("listaRequest", rService.listar());
		return "request/listRequest";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("request", new Request());
		model.addAttribute("listaEventPlanner", evplService.listar());
		return "solicitudevento/solicitudevento";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Request objRequest, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors())
			return "request/request";
		else {
			boolean flag = rService.insertar(objRequest);
			if (flag)
				return "redirect:/request/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/request/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Request> objRequest = rService.listarId(id);
		if (objRequest == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/request/listar";
		} else {

			model.addAttribute("request", objRequest);
			return "request/request";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rService.eliminar(id);
				model.put("listaRequest", rService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaRequest", rService.listar());
		}
		return "request/listRequest";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRequest", rService.listar());
		return "request/listRequest";
	}

	@RequestMapping("/irBuscar")
	public String buscar(Model model) {
		model.addAttribute("requestEvent", new Request());
		return "request/buscar";
	}

	@RequestMapping("/buscar")
	public String findBy(Map<String, Object> model, @ModelAttribute Request requestEvent)
			throws ParseException {
		List<Request> listaRequest = new ArrayList<Request>();
		EventPlanner eventPlanner = new EventPlanner();
		Planner planner = new Planner();
		Event event = new Event();

		planner.setNombre(requestEvent.getEventPlanner().getEvent().getNameEvent());
		event.setNameEvent(requestEvent.getEventPlanner().getEvent().getNameEvent());
		
		eventPlanner.setPlanner(planner);
		eventPlanner.setEvent(event);
		
		requestEvent.setEventPlanner(eventPlanner);
		listaRequest = rService.findNamePlanner(requestEvent.getEventPlanner().getPlanner().getNombre());
		if (listaRequest.isEmpty()) {
			listaRequest = rService
					.findNameEvent(requestEvent.getEventPlanner().getEvent().getNameEvent());
		}
		if (listaRequest.isEmpty()) {
			model.put("mensaje", "No se encontraron coincidencias");
		}
		model.put("listaRequest", listaRequest);
		return "request/buscar";
	}

}