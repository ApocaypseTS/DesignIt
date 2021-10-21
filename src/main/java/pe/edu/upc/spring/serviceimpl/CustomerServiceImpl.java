package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.repository.ICustomerRepository;
import pe.edu.upc.spring.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository dCustomer;

	@Override
	@Transactional
	public boolean insertar(Customer cliente) {
		Customer objCustomer = dCustomer.save(cliente);
		if (objCustomer == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Customer cliente) {
		boolean flag = false;
		try {
			dCustomer.save(cliente);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idCustomer) {
		dCustomer.deleteById(idCustomer);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> listarId(int idCustomer) {
		return dCustomer.findById(idCustomer);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> listar() {
		return dCustomer.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> buscarName(String nombre) {
		return dCustomer.buscarNombre(nombre);
	}
}
