package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Review;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer>{
	
	@Query("From Review c where c.valoracion like %:valoracion%")
	List<Review> buscarValoracion(@Param("valoracion") int valoracion);

}
