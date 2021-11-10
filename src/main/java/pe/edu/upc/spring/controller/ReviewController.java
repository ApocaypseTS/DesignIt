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


import pe.edu.upc.spring.model.Review;

import pe.edu.upc.spring.service.IReviewService;
import pe.edu.upc.spring.service.ICustomerService;
import pe.edu.upc.spring.service.IPlannerService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private IReviewService rService;
	
	@Autowired
	private ICustomerService cService;
	
	@Autowired
	private IPlannerService pService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoResena(Map<String, Object> model) {
		model.put("listaReview", rService.listar());
		return "resenaplanner/listReview";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("resenaplanner", new Review());
		model.addAttribute("listaCustomers", cService.listar());
		model.addAttribute("listaPlanners", pService.listar());
		return "resenaplanner/resenaplanner";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Review objResena, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "resenaplanner/resenaplanner";
		}else {
			boolean flag = rService.insertar(objResena);
			if (flag)
				return "redirect:/resenaplanner/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/resenaplanner/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Review objResena = rService.listarId(id).get();
		if (objResena == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/resenaplanner/listar";
		} else {
			model.addAttribute("listaCustomers", cService.listar());
			model.addAttribute("listaPlanneres", pService.listar());
			model.addAttribute("resenaplanner", objResena);
			return "resenaplanner/resenaplanner";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaReview", rService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaReview", rService.listar());
		}
		return "resenaplanner/listReview";
		}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaReview", rService.listar());
		return "resenaPrestador/listResenaPrestador";
	}
	

	
}