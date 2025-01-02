package klusjes_v2.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "klus")
public class Klus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KLUS_ID")
    private Integer klusId;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "KLANT_ID", nullable = false)
    private Klant klant;

    @Column(name = "PRIJS", nullable = false)
    private Integer prijs;

    @Column(name = "BESCHRIJVING")
    private String beschrijving;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "KLUSJESMAN_ID")
    private Klusjesman klusjesman;

    @Column(name = "RATING")
    private Integer rating;

    // Getters and Setters

    public Integer getKlusId() {
        return klusId;
    }

    public void setKlusId(Integer klusId) {
        this.klusId = klusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Integer getPrijs() {
        return prijs;
    }

    public void setPrijs(Integer prijs) {
        this.prijs = prijs;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Klusjesman getKlusjesman() {
        return klusjesman;
    }

    public void setKlusjesman(Klusjesman klusjesman) {
        this.klusjesman = klusjesman;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public Klus() {
        this.status = StatusEnum.BESCHIKBAAR;  // Example default value
    }
	public Klus(Integer klusId, String name, Klant klant, Integer prijs, String beschrijving) {
		super();
		this.klusId = klusId;
		this.name = name;
		this.klant = klant;
		this.prijs = prijs;
		this.beschrijving = beschrijving;
		this.status = StatusEnum.BESCHIKBAAR;
	}

	public Klus(String name, Klant klant, Integer prijs, String beschrijving) {
		super();
		this.name = name;
		this.klant = klant;
		this.prijs = prijs;
		this.beschrijving = beschrijving;
		this.status = StatusEnum.BESCHIKBAAR;
	}

	public Klus(Integer klusId, String name, Klant klant, Integer prijs, String beschrijving,
			@NotNull StatusEnum status, Klusjesman klusjesman, Integer rating) {
		super();
		this.klusId = klusId;
		this.name = name;
		this.klant = klant;
		this.prijs = prijs;
		this.beschrijving = beschrijving;
		this.status=StatusEnum.BESCHIKBAAR;
		this.klusjesman = klusjesman;
		this.rating = rating;
	}
    
    
    
    
}