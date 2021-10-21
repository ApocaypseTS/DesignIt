
package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Event;

public interface IEventService {

	public boolean insertar (Event event);
	public boolean modificar(Event event);
	public void eliminar(int idCustomer);
	public Optional<Event> listarId(int idEvent);
	List<Event> listar();
	
}
