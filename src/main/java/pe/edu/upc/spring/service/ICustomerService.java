package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Customer;

public interface ICustomerService {

	public boolean insertar (Customer customer);
	public boolean modificar(Customer customer);
	public void eliminar(int idCustomer);
	public Optional<Customer> listarId(int idCustomer);
	List<Customer> listar();
	List<Customer> buscarName(String name);
}