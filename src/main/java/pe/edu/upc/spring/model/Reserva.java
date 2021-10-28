package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="Reserva")
public class Reserva implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReserva;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idUbicacion", nullable=false)
	private Ubicacion ubicacion;
	
	@ManyToOne
	@JoinColumn(name="idOrden", nullable=false)
	private Orden orden;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechareserva")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechareserva;

	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(int idReserva, Usuario usuario, Ubicacion ubicacion, Orden orden, Date fechareserva) {
		super();
		this.idReserva = idReserva;
		this.usuario = usuario;
		this.ubicacion = ubicacion;
		this.orden = orden;
		this.fechareserva = fechareserva;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Date getFechareserva() {
		return fechareserva;
	}

	public void setFechareserva(Date fechareserva) {
		this.fechareserva = fechareserva;
	}

	
}
