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
	public boolean insertar(Review resenatutor) {
		Review objReview = dResena.save(resenatutor);
		if (objReview == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Review resenatutor) {
		boolean flag = false;
		try {
			dResena.save(resenatutor);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Sucedió un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idReseña) {
		dResena.deleteById(idReseña);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Review> listarId(int idReseña) {
		return dResena.findById(idReseña);
	}

	@Override
	@Transactional
	public List<Review> listar() {
		return dResena.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Review> buscarValoracion(int valoracion) {
		return dResena.buscarValoracion(valoracion);
	}
}
