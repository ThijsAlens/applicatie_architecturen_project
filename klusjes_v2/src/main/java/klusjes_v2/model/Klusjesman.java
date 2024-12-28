package klusjes_v2.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KLUSJESMAN_ID")
    private Integer klusjesmanId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "people", referencedColumnName = "USERNAME")
    private People people;

	public Integer getKlusjesmanId() {
        return klusjesmanId;
    }

    public void setKlusjesmanId(Integer klusjesmanId) {
        this.klusjesmanId = klusjesmanId;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

	public Klusjesman(@NotBlank Integer klusjesmanId, People people) {
		super();
		this.klusjesmanId = klusjesmanId;
		this.people = people;
	}

	public Klusjesman(People people) {
		super();
		this.people = people;
	}
    
    public float getRating() {
    	//TODO
    	return -1;
    }


    
    
    
}