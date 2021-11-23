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
@Table(name = "review")
public class Review implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	private int idResena;
	
	@Column(name= "valoration", nullable= false, length= 10)
	private int valoration;
	
	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "idPlanner", nullable = false)
	private Planner planner;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int idResena, int valoration, Customer customer, Planner planner) {
		super();
		this.idResena = idResena;
		this.valoration = valoration;
		this.customer = customer;
		this.planner = planner;
	}

	public int getIdResena() {
		return idResena;
	}

	public void setIdResena(int idResena) {
		this.idResena = idResena;
	}

	public int getValoration() {
		return valoration;
	}

	public void setValoration(int valoration) {
		this.valoration = valoration;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Planner getPlanner() {
		return planner;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}


	
	
}