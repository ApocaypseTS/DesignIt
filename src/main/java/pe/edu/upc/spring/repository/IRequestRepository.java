package pe.edu.upc.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Request;


@Repository
public interface IRequestRepository extends JpaRepository<Request, Integer> {

	
	@Query("From Request s where s.eventPlanner.planner.nombre like %:nombre%")
	List<Request> buscarNombrePlanner(@Param("nombre") String nombre);
	
	@Query("From Request s where s.eventPlanner.curso.nombreEvent like %:nombreEvent%")
	List<Request> buscarNombreEvent(@Param("nombreEvent") String nombreEvent);
	
}