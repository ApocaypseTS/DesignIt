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
@Table(name ="Disponible")
public class Disponible implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDisponible;
	
	@ManyToOne
	@JoinColumn(name="idOrden", nullable=false)
	private Orden orden;
	
	private boolean disponible;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechadisponible")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechadisponible;

	public Disponible() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Disponible(int idDisponible, Orden orden, boolean disponible, Date fechadisponible) {
		super();
		this.idDisponible = idDisponible;
		this.orden = orden;
		this.disponible = disponible;
		this.fechadisponible = fechadisponible;
	}

	public int getIdDisponible() {
		return idDisponible;
	}

	public void setIdDisponible(int idDisponible) {
		this.idDisponible = idDisponible;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Date getFechadisponible() {
		return fechadisponible;
	}

	public void setFechadisponible(Date fechadisponible) {
		this.fechadisponible = fechadisponible;
	}
	
	
}
