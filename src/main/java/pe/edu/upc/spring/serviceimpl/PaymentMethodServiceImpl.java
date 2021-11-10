 package pe.edu.upc.spring.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.PaymentMethod;
import pe.edu.upc.spring.service.IPaymentMethodService;
import pe.edu.upc.spring.repository.IPaymentMethodRepository;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {

	@Autowired
	private IPaymentMethodRepository dPaymentMethod;
	
	@Override
	@Transactional
	public boolean insertar(PaymentMethod metodopago) {
		PaymentMethod objPaymentMethod = dPaymentMethod.save(metodopago);
			if (objPaymentMethod == null)
				return false;
			else
				return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(PaymentMethod metodopago) {
		boolean flag = false;
		try {
			dPaymentMethod.save(metodopago);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idPaymentMethod) {
		dPaymentMethod.deleteById(idPaymentMethod);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentMethod> listarId(int idPaymentMethod) {
		return dPaymentMethod.findById(idPaymentMethod);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> listar() {
		return dPaymentMethod.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> buscarName(String nombre) {
		return dPaymentMethod.buscarNombre(nombre);
	}
	
	
	
	
	
}
	