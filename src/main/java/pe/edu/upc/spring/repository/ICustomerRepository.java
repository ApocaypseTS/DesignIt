package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("From Customer c where c.nombre like %:nombre%")
	List<Customer> buscarNombre(@Param("nombre") String nombre);
	
	
	/*@Query("From Customer c where c.reviews.planner.idPlanner like %:idPlanner%")
	List<Customer> buscarPlanner(@Param("idPlanner") String idPlanner);*/
	
}
