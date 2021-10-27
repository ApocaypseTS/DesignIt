package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rol")
public class Rol implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;
	
	@Column(name = "nombreRol", length = 25, nullable=false)
	public String nameRol;

	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(int idRol, String nameRol) {
		super();
		this.idRol = idRol;
		this.nameRol = nameRol;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNameRol() {
		return nameRol;
	}

	public void setNameRol(String nameRol) {
		this.nameRol = nameRol;
	}

	
}
