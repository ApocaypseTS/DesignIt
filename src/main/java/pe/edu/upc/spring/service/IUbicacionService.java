package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Ubicacion;

public interface IUbicacionService {

	public boolean grabar(Ubicacion ubicacion);
	public void eliminar(int idUbicacion);
	public Optional<Ubicacion> listarId(int idUbicacion);
	public List<Ubicacion> listar();
}
