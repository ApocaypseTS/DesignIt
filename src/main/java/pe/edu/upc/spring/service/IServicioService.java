package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Servicio;

public interface IServicioService {

	public boolean grabar(Servicio servicio);
	public void eliminar(int idServicio);
	public Optional<Servicio> listarId(int idServicio);
	public List<Servicio> listar();
}
