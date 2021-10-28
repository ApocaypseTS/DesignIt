package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Disponible;
import pe.edu.upc.spring.repository.IDisponibleRepository;
import pe.edu.upc.spring.service.IDisponibleService;

@Service
public class DisponibleServiceImpl implements IDisponibleService{

	@Autowired
	private IDisponibleRepository dDisponible;
	
	@Override
	@Transactional
	public boolean grabar(Disponible disponible) {
		Disponible objDisponible = dDisponible.save(disponible);
		if(objDisponible == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idDisponible) {
		dDisponible.deleteById(idDisponible);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Disponible> listarId(int idDisponible) {
		return dDisponible.findById(idDisponible);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Disponible> listar() {
		return dDisponible.findAll();
	}


}
