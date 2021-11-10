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
@Table(name = "request")
public class Request implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idRequest;
    
    @Column(name="fecha_clase", nullable=false, length=50)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaEvento;
    
    @Column(name="cantidad_de_hora_por_evento", nullable=false, length=50)
    private int horasEvento;

	@Transient
	private double montoTotal;
    
    @ManyToOne
    @JoinColumn(name="idEventPlanner", nullable=false)
    private EventPlanner eventPlanner;
    

    public Request() {
        super();
        // TODO Auto-generated constructor stub
    }


	public Request(int idRequest, Date fechaEvento, int horasEvento, double montoTotal,
			EventPlanner eventPlanner) {
		super();
		this.idRequest = idRequest;
		this.fechaEvento = fechaEvento;
		this.horasEvento = horasEvento;
		this.montoTotal = eventPlanner.getCostoHora()*horasEvento;
		this.eventPlanner = eventPlanner;
	}


	public int getIdRequest() {
		return idRequest;
	}


	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}


	public Date getFechaEvento() {
		return fechaEvento;
	}


	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}


	public int getHorasEvento() {
		return horasEvento;
	}


	public void setHorasEvento(int horasEvento) {
		this.horasEvento = horasEvento;
	}


	public double getMontoTotal() {
		return eventPlanner.getCostoHora()*horasEvento;
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