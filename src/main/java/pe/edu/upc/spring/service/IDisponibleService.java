package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Disponible;

public interface IDisponibleService {

	public boolean grabar(Disponible disponible);
	public void eliminar(int idDisponible);
	public Optional<Disponible> listarId(int idDisponible);
	public List<Disponible> listar();
}
