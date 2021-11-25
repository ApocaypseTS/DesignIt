package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idCustomer;

	@NotBlank(message = "Debe ingresar correo")
	@Column(name="correo_electronico", nullable=false, length=50)
	private String correo;
	
	@NotBlank(message = "Debe ingresar contrasena")
	@Column(name="contrasena", nullable=false, length=50)
	private String contrasena;
	
	@NotBlank(message = "Debe ingresar el nombre")
	@Pattern(regexp = "[A-Za-zñ ]+", message="El nombre solo puede tener letras")
	@Column(name="nombre", nullable=false, length=50)
	private String nombre;
	
	@NotBlank(message = "Debe ingresar el apellido")
	@Pattern(regexp = "[A-Za-zñ ]+", message="El apellido solo puede tener letras")
	@Column(name="apellido", nullable=false, length=50)
	private String apellido;

	@NotBlank(message = "Debe ingresar codigo al cliente")
	@Size(min = 8, max = 8, message = "El DNI solo debe tener 8 digitos")
	@Pattern(regexp = "[0-9]+", message="El DNI solo puede tener números")
	@Column(name="dni", nullable=false, length=8)
	private String dni;
	
	@NotBlank(message = "Debe ingresar codigo al cliente")
	@Size(min = 9, max = 9, message = "El telefono solo debe tener 9 digitos")
	@Pattern(regexp = "[0-9]+", message="El teléfono solo puede tener números")
	@Column(name="telefono", nullable=false, length=9)
	private String telefono;
	
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Review> reviews;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int idCustomer,
			@Size(min = 10, max = 50, message = "El correo electrónico es inválido") String correo,
			@Size(min = 5, max = 20, message = "La contrasena solo debe tener entre 5 a 20 caracteres") String contrasena,
			@Pattern(regexp = "[0-9]+", message = "El nombre solo puede tener letras") String nombre,
			@Pattern(regexp = "[0-9]+", message = "El apellido solo puede tener letras") String apellido,
			@Size(min = 8, max = 28, message = "La contrasena solo debe tener entre 5 a 20 caracteres") @Pattern(regexp = "[A-Za-zñ ]+", message = "El teléfono solo puede tener números") String dni,
			@Size(min = 5, max = 20, message = "La contrasena solo debe tener entre 5 a 20 caracteres") @Pattern(regexp = "[A-Za-zñ ]+", message = "El teléfono solo puede tener números") String telefono,
			Set<Review> reviews) {
		super();
		this.idCustomer = idCustomer;
		this.correo = correo;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;

		this.reviews = reviews;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Set<Review> getReviewes() {
		return reviews;
	}

	public void setReviewes(Set<Review> reviews) {
		this.reviews = reviews;
	}

	

	
	
}