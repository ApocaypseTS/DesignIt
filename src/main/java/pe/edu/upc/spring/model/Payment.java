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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "payment")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idPayment;


	@Column(name="fecha", nullable=false, length=50)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@Column(name="numero_tarjeta", nullable=false, length=16)
	private String numeroTarj;

	@Column(name="caducidad", nullable=false, length=4)
	private int caducidad;
	
	@Column(name="nombre_titular", nullable=false, length=50)
	private String nombreTitular;
	
	@Column(name="codigo_seguridad", nullable=false, length=3)
	private int codigoSeguridad;	
	
	@ManyToOne
	@JoinColumn(name="idMetodo", nullable=false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(name="idRequest", nullable=false)
	private Request request;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int idPayment, String monto, Date fecha, String numeroTarj, int caducidad, String nombreTitular,
			int codigoSeguridad, PaymentMethod paymentMethod, Request request) {
		super();
		this.idPayment = idPayment;
		this.fecha = fecha;
		this.numeroTarj = numeroTarj;
		this.caducidad = caducidad;
		this.nombreTitular = nombreTitular;
		this.codigoSeguridad = codigoSeguridad;
		this.paymentMethod = paymentMethod;
		this.request = request;
	}

	public int getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNumeroTarj() {
		return numeroTarj;
	}

	public void setNumeroTarj(String numeroTarj) {
		this.numeroTarj = numeroTarj;
	}

	public int getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(int caducidad) {
		this.caducidad = caducidad;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public int getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(int codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
}