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
import java.text.ParseException;
import pe.edu.upc.spring.model.Event;
import pe.edu.upc.spring.service.IEventService;

@Controller
@RequestMapping("/event")
public class EventController {

	@Autowired
	private IEventService evService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "event/bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoPlanners(Map<String, Object> model) {
		model.put("listaEvents", evService.listar());
		return "event/listEvent";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("event", new Event());
		return "event/event";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Event objEvent, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "event/event";
		else {
			boolean flag = evService.insertar(objEvent);
			if (flag)
				return "redirect:/event/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/event/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Event> objEvent = evService.listarId(id);
		if (objEvent == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/event/listar";
		} else {
			model.addAttribute("event", objEvent);
			return "event/event";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				evService.eliminar(id);
				model.put("listaEvent", evService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaEvent", evService.listar());
		}
		return "event/listEvent";
		}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEvent", evService.listar());
		return "evento/listEvento";
	}
}