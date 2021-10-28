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
@Table(name ="Orden")
public class Orden implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrden;
	
	@Column(name = "resenia", length = 50, nullable=false)
	private String resenia;
	
	private int precio;

	@ManyToOne
	@JoinColumn(name="idServicio", nullable = false)
	private Servicio servicio;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false)
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaemitido")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaemitido;

	public Orden() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orden(int idOrden, String resenia, int precio, Servicio servicio, Usuario usuario, Date fechaemitido) {
		super();
		this.idOrden = idOrden;
		this.resenia = resenia;
		this.precio = precio;
		this.servicio = servicio;
		this.usuario = usuario;
		this.fechaemitido = fechaemitido;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public String getResenia() {
		return resenia;
	}

	public void setResenia(String resenia) {
		this.resenia = resenia;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaemitido() {
		return fechaemitido;
	}

	public void setFechaemitido(Date fechaemitido) {
		this.fechaemitido = fechaemitido;
	}
	
	
}
