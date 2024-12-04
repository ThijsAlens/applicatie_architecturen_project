package krb.soit.application_architectures.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "AA_CUSTOMERS")
public class Costumer {
	@Column(name = "NAME")
	@NotBlank
	private String name;
	@Id
	@Column(name = "EMAIL")
	@NotBlank
	private String email;
	@Column(name = "PASSWD")
	@NotBlank
	private String password;
	@Column(name = "ADDRESS")
	@NotBlank
	private String address;
	@Column(name = "PCODE")
	@Min(0)
	private int pcode;
	@Column(name = "CITY")
	@NotBlank
	private String city;
	@Column(name = "ENABLED")
	private int enabled = 1;
	
/*============================================*/
	
	public Costumer(String email, String password, String address, int pcode, String city, int enabled) {
		this.email = email;
		this.password = password;
		this.address = address;
		this.pcode = pcode;
		this.city = city;
		this.enabled = enabled;
	}
	
	public Costumer() {
	}

/*============================================*/
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	
}
