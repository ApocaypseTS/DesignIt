package pe.edu.upc.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.PaymentMethod;

@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

	@Query("From PaymentMethod c where c.nombre like %:nombre%")
	List<PaymentMethod> buscarNombre(@Param("nombre")String nombre);
	
}
