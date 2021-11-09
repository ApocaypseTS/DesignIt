package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Request;
import pe.edu.upc.spring.repository.IRequestRepository;
import pe.edu.upc.spring.service.IRequestService;

@Service
public class RequestServiceImpl implements IRequestService {

	@Autowired
	private IRequestRepository dRequest;

	@Override
	@Transactional
	public boolean insertar(Request solicitudclase) {
		Request objRequest = dRequest.save(solicitudclase);
		if (objRequest == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Request solicitudclase) {
		boolean flag = false;
		try {
			dRequest.save(solicitudclase);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idRequest) {
		dRequest.deleteById(idRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Request> listarId(int idRequest) {
		return dRequest.findById(idRequest);
	}

	@Override
	public List<Request> listar(){
		List<Request> lista = dRequest.findAll();
		return lista;
	}


	@Override
	@Transactional(readOnly = true)
	public List<Request> findNamePlanner(String nombre) {
		return dRequest.SearchByNamePlanner(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Request> findNameEvent(String nombre) {
		return dRequest.SearchByNameEvent(nombre);
	}

	
}