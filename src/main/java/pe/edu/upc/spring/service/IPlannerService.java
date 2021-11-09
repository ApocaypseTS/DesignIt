package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Planner;

public interface IPlannerService {

	public boolean insertar (Planner planner);
	public boolean modificar(Planner planner);
	public void eliminar(int idPlanner);
	public Optional<Planner> listarId(int idPlanner);
	List<Planner> listar();
	List<Planner> buscarName(String name);
}