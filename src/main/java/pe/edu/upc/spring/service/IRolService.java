package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Rol;

public interface IRolService {

	public boolean grabar(Rol rol);
	public void eliminar(int idRol);
	public Optional<Rol> listarId(int idRol);
	public List<Rol> listar();
}
