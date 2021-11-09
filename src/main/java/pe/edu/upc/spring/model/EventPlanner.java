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
@Table(name = "eventplanner")
public class EventPlanner implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idEventPlanner;
	
	@Column(name="nota", nullable=false, length=2)
	private String nota;

	@Column(name="costoPorHora", nullable=false, length=3)
	private double costoHora;
	
	@Column(name="horarioDisponible", nullable=false, length=90)
	private String horarioDisponible;

	@ManyToOne
	@JoinColumn(name = "idEvent", nullable = false)
	private Event event;
	
	@ManyToOne
	@JoinColumn(name = "idPlanner", nullable = false)
	private Planner planner;

	public EventPlanner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventPlanner(int idEventPlanner, String nota, double costoHora, String horarioDisponible, Event event,
			Planner planner) {
		super();
		this.idEventPlanner = idEventPlanner;
		this.nota = nota;
		this.costoHora = costoHora;
		this.horarioDisponible = horarioDisponible;
		this.event = event;
		this.planner = planner;
	}

	public int getIdEventPlanner() {
		return idEventPlanner;
	}

	public void setIdEventPlanner(int idEventPlanner) {
		this.idEventPlanner = idEventPlanner;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public double getCostoHora() {
		return costoHora;
	}

	public void setCostoHora(double costoHora) {
		this.costoHora = costoHora;
	}

	public String getHorarioDisponible() {
		return horarioDisponible;
	}

	public void setHorarioDisponible(String horarioDisponible) {
		this.horarioDisponible = horarioDisponible;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Planner getPlanner() {
		return planner;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}


	
	
	

	

}