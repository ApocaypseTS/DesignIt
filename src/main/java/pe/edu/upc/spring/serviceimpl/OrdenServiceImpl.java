package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Orden;
import pe.edu.upc.spring.repository.IOrdenRepository;
import pe.edu.upc.spring.service.IOrdenService;

@Service
public class OrdenServiceImpl implements IOrdenService{

	@Autowired
	private IOrdenRepository dOrden;
	
	@Override
	@Transactional
	public boolean grabar(Orden orden) {
		Orden objOrden = dOrden.save(orden);
		if(objOrden == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idOrden) {
		dOrden.deleteById(idOrden);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Orden> listarId(int idOrden) {
		return dOrden.findById(idOrden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Orden> listar() {
		return dOrden.findAll();
	}


}
