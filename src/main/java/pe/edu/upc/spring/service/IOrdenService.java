package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Orden;

public interface IOrdenService {

	public boolean grabar(Orden orden);
	public void eliminar(int idOrden);
	public Optional<Orden> listarId(int idOrden);
	public List<Orden> listar();
}
