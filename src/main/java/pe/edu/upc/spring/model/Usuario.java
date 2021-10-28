package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="Usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@Column(name = "nombreUsuario", length = 25, nullable=false)
	private String nameUsuario;
	
	@Column(name = "apellido", length = 25, nullable=false)
	private String apellido;
	
	@Column(name = "email", length = 50, nullable=false)
	private String email;
	
	@Column(name = "contrasena", length = 50, nullable=false)
	private String contrasena;

	@Column(name = "profesion", length = 50, nullable=true)
	private String profesion;
	
	@ManyToOne
	@JoinColumn(name="idRol", nullable = false)
	private Rol rol;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int idUsuario, String nameUsuario, String apellido, String email, String contrasena,
			String profesion, Rol rol) {
		super();
		this.idUsuario = idUsuario;
		this.nameUsuario = nameUsuario;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.profesion = profesion;
		this.rol = rol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
}
