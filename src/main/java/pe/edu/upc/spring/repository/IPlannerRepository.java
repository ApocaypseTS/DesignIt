package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Planner;

@Repository
public interface IPlannerRepository extends JpaRepository<Planner, Integer> {

	@Query("From Planner p where p.nombre like %:nombre%")
	List<Planner> buscarNombre(@Param("nombre") String nombre);
}
