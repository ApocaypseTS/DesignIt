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

import pe.edu.upc.spring.model.Payment;
import pe.edu.upc.spring.service.IPaymentMethodService;
import pe.edu.upc.spring.service.IPaymentService;
import pe.edu.upc.spring.service.IRequestService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private IPaymentService pService;
	
	@Autowired
	private IPaymentMethodService mService;

	@Autowired
	private IRequestService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoPayments(Map<String, Object> model) {
		model.put("listaPayments", pService.listar());
		return "payment/listPayment";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("payment", new Payment());
		model.addAttribute("listaPaymentMethod", mService.listar());
		model.addAttribute("listaRequest", rService.listar());
		return "payment/payment";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Payment objPayment, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "payment/payment";
		else {
			boolean flag = pService.insertar(objPayment);
			if (flag)
				return "redirect:/payment/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/payment/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Payment> objPayment = pService.listarId(id);
		if (objPayment == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/payment/listar";
		} else {
			model.addAttribute("listaPaymentMethod", mService.listar());
			model.addAttribute("listaRequest", rService.listar());
			model.addAttribute("payment", objPayment);
			return "payment/payment";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPayments", pService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPayment", pService.listar());
		}
		return "payment/listPayment";
		}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPayments", pService.listar());
		return "payment/listPayment";
	}
}