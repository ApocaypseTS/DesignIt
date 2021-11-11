package pe.edu.upc.spring.controller;
import java.text.ParseException;
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
import pe.edu.upc.spring.model.Planner;
import pe.edu.upc.spring.service.IPlannerService;

@Controller
@RequestMapping("/planner")
public class PlannerController {

	@Autowired
	private IPlannerService pService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	
	@RequestMapping("/")
	public String irPaginaListadoPlanners(Map<String, Object> model) {
		model.put("listaPlanners", pService.listar());
		return "prestador/listPrestador";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("planner", new Planner());
		return "prestador/prestador";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Planner objPlanner, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "prestador/prestador";
		else {
			boolean flag = pService.insertar(objPlanner);
			if (flag)
				return "redirect:/planner/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/planner/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Planner> objPlanner = pService.listarId(id);
		if (objPlanner == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/planner/listar";
		} else {
			model.addAttribute("planner", objPlanner);
			return "prestador/prestador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPlanners", pService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPlanners", pService.listar());
		}
		return "prestador/listPrestador";
		}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPlanners", pService.listar());
		return "prestador/listPrestador";
	}
}