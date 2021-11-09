package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.EventPlanner;

public interface IEventPlannerService {

	public boolean insertar (EventPlanner eventPlanner);
	public boolean modificar(EventPlanner eventPlanner);
	public void eliminar(int idEventPlanner);
	public Optional<EventPlanner> listarId(int idEventPlanner);
	List<EventPlanner> listar();
	
}