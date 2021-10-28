package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Reserva;

public interface IReservaService {

	public boolean grabar(Reserva reserva);
	public void eliminar(int idReserva);
	public Optional<Reserva> listarId(int idReserva);
	public List<Reserva> listar();
}
