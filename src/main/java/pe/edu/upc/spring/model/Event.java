package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "events")
public class Event implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idEvent;

	@Column(name="name_event", nullable=false, length=50)
	private String nameEvent;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(int idEvent, String nameEvent) {
		super();
		this.idEvent = idEvent;
		this.nameEvent = nameEvent;
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	
	
}