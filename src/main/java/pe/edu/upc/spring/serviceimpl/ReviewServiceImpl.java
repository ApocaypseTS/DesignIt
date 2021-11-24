package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Review;
import pe.edu.upc.spring.repository.IReviewRepository;
import pe.edu.upc.spring.service.IReviewService;

@Service
public class ReviewServiceImpl implements IReviewService {
	@Autowired
	private IReviewRepository dResena;

	@Override
	@Transactional
	public boolean insertar(Review review) {
		Review objReview = dResena.save(review);
		if (objReview == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Review review) {
		boolean flag = false;
		try {
			dResena.save(review);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idResena) {
		dResena.deleteById(idResena);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Review> listarId(int idResena) {
		return dResena.findById(idResena);
	}

	@Override
	@Transactional
	public List<Review> listar() {
		return dResena.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Review> buscarValoracion(int valoracion) {
		return dResena.SearchValoration(valoracion);
	}
}