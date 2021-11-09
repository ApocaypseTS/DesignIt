package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Review;


public interface IReviewService {

	public boolean insertar (Review review);
	public boolean modificar(Review review);
	public void eliminar(int idReseña);
	public Optional<Review> listarId(int idReview);
	List<Review> listar();
	List<Review> buscarValoracion(int valoracion);

}