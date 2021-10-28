package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Area;
import pe.edu.upc.spring.repository.IAreaRepository;
import pe.edu.upc.spring.service.IAreaService;

@Service
public class AreaServiceImpl implements IAreaService{

	@Autowired
	private IAreaRepository dArea;
	
	@Override
	@Transactional
	public boolean grabar(Area area) {
		Area objArea = dArea.save(area);
		if(objArea == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idArea) {
		dArea.deleteById(idArea);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Area> listarId(int idArea) {
		return dArea.findById(idArea);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Area> listar() {
		return dArea.findAll();
	}


}
