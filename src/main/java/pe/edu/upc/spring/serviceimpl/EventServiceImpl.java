package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Event;
import pe.edu.upc.spring.repository.IEventRepository;
import pe.edu.upc.spring.service.IEventService;

@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	private IEventRepository dEvent;

	@Override
	@Transactional
	public boolean insertar(Event curso) {
		Event objEvent = dEvent.save(curso);
		if (objEvent == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Event curso) {
		boolean flag = false;
		try {
			dEvent.save(curso);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idEvent) {
		dEvent.deleteById(idEvent);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Event> listarId(int idEvent) {
		return dEvent.findById(idEvent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Event> listar() {
		return dEvent.findAll();
	}

	
}
