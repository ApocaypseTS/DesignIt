package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ubicacion")
public class Ubicacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUbicacion;
	
	@Column(name = "nombreDistrito", length = 50, nullable=false)
	public String nameDistrito;
	
	@Column(name = "nombreCalle", length = 50, nullable=false)
	public String nameCalle;

	public Ubicacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ubicacion(int idUbicacion, String nameDistrito, String nameCalle) {
		super();
		this.idUbicacion = idUbicacion;
		this.nameDistrito = nameDistrito;
		this.nameCalle = nameCalle;
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getNameDistrito() {
		return nameDistrito;
	}

	public void setNameDistrito(String nameDistrito) {
		this.nameDistrito = nameDistrito;
	}

	public String getNameCalle() {
		return nameCalle;
	}

	public void setNameCalle(String nameCalle) {
		this.nameCalle = nameCalle;
	}


	
}
