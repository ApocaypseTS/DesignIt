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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "solicitudclase")
public class Request implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idRequest;
    
    @Column(name="fecha_clase", nullable=false, length=50)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaClase;
    
    @Column(name="cantidad_de_hora_por_curso", nullable=false, length=50)
    private int horasCurso;

	@Transient
	private double montoTotal;
    
    @ManyToOne
    @JoinColumn(name="idEventPlanner", nullable=false)
    private EventPlanner eventPlanner;
    

    public Request() {
        super();
        // TODO Auto-generated constructor stub
    }


	public Request(int idRequest, Date fechaClase, int horasCurso, double montoTotal,
			EventPlanner eventPlanner) {
		super();
		this.idRequest = idRequest;
		this.fechaClase = fechaClase;
		this.horasCurso = horasCurso;
		this.montoTotal = eventPlanner.getCostoHora()*horasCurso;
		this.eventPlanner = eventPlanner;
	}


	public int getIdRequest() {
		return idRequest;
	}


	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}


	public Date getFechaClase() {
		return fechaClase;
	}


	public void setFechaClase(Date fechaClase) {
		this.fechaClase = fechaClase;
	}


	public int getHorasCurso() {
		return horasCurso;
	}


	public void setHorasCurso(int horasCurso) {
		this.horasCurso = horasCurso;
	}


	public double getMontoTotal() {
		return eventPlanner.getCostoHora()*horasCurso;
	}


	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}


	public EventPlanner getEventPlanner() {
		return eventPlanner;
	}


	public void setEventPlanner(EventPlanner eventPlanner) {
		this.eventPlanner = eventPlanner;
	}


	
}