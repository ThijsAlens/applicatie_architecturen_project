package krb.soit.application_architectures.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "AA_RESERVATIONS")
public class Rent {
	@Id
	@Column(name = "ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "COSTUMER")
	private Costumer customer;
	
	@ManyToOne
	@JoinColumn(name = "PICKUPLOCATION")
	private Location pickupLocation;
	
	@ManyToOne
	@JoinColumn(name = "DROPOFFLOCATION")
	private Location dropoffLocation;
	
	@ManyToOne
	@JoinColumn(name = "CAR")
	private Car car;
	
	@Column(name = "DAYS")
	@Max(100)
	private int days;
	
	@Column(name = "FROMDATE")
	private Date fromDate;
	
	@Column(name = "RESDATE")
	private Date resDate;
/*============================================*/
	
	public Rent(int id, int days, Date fromDate, Date resDate) {
		this.id = id;
		this.days = days;
		this.fromDate = fromDate;
		this.resDate = resDate;
	}

	public Rent() {
	}

/*============================================*/

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	
}
