package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Payment;

public interface IPaymentService {

	public boolean insertar (Payment payment);
	public boolean modificar(Payment payment);
	public void eliminar(int idPayment);
	public Optional<Payment> listarId(int idPayment);
	List<Payment> listar();
}
