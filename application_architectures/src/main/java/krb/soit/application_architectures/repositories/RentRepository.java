package krb.soit.application_architectures.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import krb.soit.application_architectures.model.Location;
import krb.soit.application_architectures.model.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

}
