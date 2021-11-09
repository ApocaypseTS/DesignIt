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

import pe.edu.upc.spring.model.PaymentMethod;
import pe.edu.upc.spring.service.IPaymentMethodService;


@Controller
@RequestMapping("/methodpayment")
public class PaymentMethodController {

	@Autowired
	private IPaymentMethodService pmService;

	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoPaymentMethod(Map<String, Object> model) {
		model.put("listaPaymentMethod", pmService.listar());
		return "methodpayment/listPaymentMethod";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("methodpayment", new PaymentMethod());
		return "methodpayment/methodpayment";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute PaymentMethod objPaymentMethod, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "methodpayment/methodpayment";
		else {
			boolean flag = pmService.insertar(objPaymentMethod);
			if (flag)
				return "redirect:/methodpayment/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/methodpayment/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<PaymentMethod> objPaymentMethod = pmService.listarId(id);
		if (objPaymentMethod == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/methodpayment/listar";
		} else {
			model.addAttribute("methodpayment", objPaymentMethod);
			return "methodpayment/methodpayment";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pmService.eliminar(id);
				model.put("listaPaymentMethod", pmService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPaymentMethod", pmService.listar());
		}
		return "methodpayment/listPaymentMethod";
		}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPaymentMethod", pmService.listar());
		return "methodpayment/listPaymentMethod";
	}
	
	
	
	
	
	
}