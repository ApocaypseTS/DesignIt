package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Payment;
import pe.edu.upc.spring.repository.IPaymentRepository;
import pe.edu.upc.spring.service.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private IPaymentRepository dPayment;

	@Override
	@Transactional
	public boolean insertar(Payment pago) {
		Payment objPayment = dPayment.save(pago);
		if (objPayment == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Payment pago) {
		boolean flag = false;
		try {
			dPayment.save(pago);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idPayment) {
		dPayment.deleteById(idPayment);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Payment> listarId(int idPayment) {
		return dPayment.findById(idPayment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Payment> listar() {
		return dPayment.findAll();
	}

}
