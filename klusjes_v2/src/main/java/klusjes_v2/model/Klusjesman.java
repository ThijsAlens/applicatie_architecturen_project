package klusjes_v2.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "klusjesman")
public class Klusjesman {

    @Id
    @NotBlank
    @Column(name = "KLUSJESMAN_ID")
    private Integer klantId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "people", referencedColumnName = "USERNAME")
    private People people;
    
    @Column(name="RATING")
    private float rating;

    public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Integer getKlantId() {
        return klantId;
    }

    public void setKlantId(Integer klantId) {
        this.klantId = klantId;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
    
}