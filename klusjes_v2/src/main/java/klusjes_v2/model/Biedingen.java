package klusjes_v2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "biedingen")
public class Biedingen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    @ManyToOne
    @JoinColumn(name = "KLUS_ID", referencedColumnName = "KLUS_ID")
    private Klus klus;  

    @ManyToOne
    @JoinColumn(name = "KLUSJESMAN_ID", referencedColumnName = "KLUSJESMAN_ID")
    private Klusjesman klusjesman;

	public Biedingen(int id, Klus klus, Klusjesman klusjesman) {
		super();
		this.id = id;
		this.klus = klus;
		this.klusjesman = klusjesman;
	}

	public Biedingen(Klus klus, Klusjesman klusjesman) {
		super();
		this.klus = klus;
		this.klusjesman = klusjesman;
	}  

}
