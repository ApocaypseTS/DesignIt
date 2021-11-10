 package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Planner;
import pe.edu.upc.spring.repository.IPlannerRepository;
import pe.edu.upc.spring.service.IPlannerService;

@Service
public class PlannerServiceImpl implements IPlannerService {

	@Autowired
	private IPlannerRepository dPlanner;

	@Override
	@Transactional
	public boolean insertar(Planner planner) {
		Planner objPlanner = dPlanner.save(planner);
		if (objPlanner == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Planner planner) {
		boolean flag = false;
		try {
			dPlanner.save(planner);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idPlanner) {
		dPlanner.deleteById(idPlanner);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Planner> listarId(int idPlanner) {
		return dPlanner.findById(idPlanner);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Planner> listar() {
		return dPlanner.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Planner> buscarName(String nombre) {
		return dPlanner.buscarNombre(nombre);
	}
}