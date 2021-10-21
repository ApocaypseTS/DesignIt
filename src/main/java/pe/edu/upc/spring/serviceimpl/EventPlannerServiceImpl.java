package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.EventPlanner;
import pe.edu.upc.spring.repository.IEventPlannerRepository;
import pe.edu.upc.spring.service.IEventPlannerService;

@Service
public class EventPlannerServiceImpl implements IEventPlannerService {

	@Autowired
	private IEventPlannerRepository dEventPlanner;

	@Override
	@Transactional
	public boolean insertar(EventPlanner cursotutor) {
		EventPlanner objCurso = dEventPlanner.save(cursotutor);
		if (objCurso == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(EventPlanner cursotutor) {
		boolean flag = false;
		try {
			dEventPlanner.save(cursotutor);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idEventPlanner) {
		dEventPlanner.deleteById(idEventPlanner);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<EventPlanner> listarId(int idEventPlanner) {
		return dEventPlanner.findById(idEventPlanner);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EventPlanner> listar() {
		return dEventPlanner.findAll();
	}

	
}
