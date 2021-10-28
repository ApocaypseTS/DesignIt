package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Area;

public interface IAreaService {

	public boolean grabar(Area area);
	public void eliminar(int idArea);
	public Optional<Area> listarId(int idArea);
	public List<Area> listar();
}
