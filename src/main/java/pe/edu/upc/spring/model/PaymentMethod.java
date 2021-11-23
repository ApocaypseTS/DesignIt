package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "paymentMethod")

public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idMethod;
	

	@NotBlank(message = "Debe ingresar su nombre")
	@Pattern(regexp = "[A-Za-zñ ]+", message="El nombre solo puede tener letras")
	@Column(name= "name", nullable= false, length=50)
	private String name;
	
	public PaymentMethod() {
		super();
	}

	public PaymentMethod(int idMethod, String name) {
		super();
		this.idMethod = idMethod;
		this.name = name;
	}

	public int getIdMethod() {
		return idMethod;
	}

	public void setIdMethod(int idMethod) {
		this.idMethod = idMethod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}