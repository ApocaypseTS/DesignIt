package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.repository.IReservaRepository;
import pe.edu.upc.spring.service.IReservaService;

@Service
public class ReservaServiceImpl implements IReservaService{

	@Autowired
	private IReservaRepository dReserva;
	
	@Override
	@Transactional
	public boolean grabar(Reserva reserva) {
		Reserva objReserva = dReserva.save(reserva);
		if(objReserva == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idReserva) {
		dReserva.deleteById(idReserva);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Reserva> listarId(int idReserva) {
		return dReserva.findById(idReserva);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reserva> listar() {
		return dReserva.findAll();
	}


}
