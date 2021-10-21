
package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Request;

public interface IRequestService {

	public boolean insertar (Request request);
	public boolean modificar(Request request);
	public void eliminar(int idRequest);
	public Optional<Request> listarId(int idRequest);
	List<Request> listar();
	List<Request> buscarNamePlanner(String name);
	List<Request> buscarNameEvent(String nameEvent);
	
}
