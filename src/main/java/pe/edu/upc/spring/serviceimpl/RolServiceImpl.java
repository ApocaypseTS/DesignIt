package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Rol;
import pe.edu.upc.spring.repository.IRolRepository;
import pe.edu.upc.spring.service.IRolService;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRolRepository dRol;
	
	@Override
	@Transactional
	public boolean grabar(Rol rol) {
		Rol objRol = dRol.save(rol);
		if(objRol == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idRol) {
		dRol.deleteById(idRol);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Rol> listarId(int idRol) {
		return dRol.findById(idRol);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> listar() {
		return dRol.findAll();
	}


}
