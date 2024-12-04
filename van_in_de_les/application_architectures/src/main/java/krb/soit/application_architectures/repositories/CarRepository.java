package krb.soit.application_architectures.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import krb.soit.application_architectures.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	
}
