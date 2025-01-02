package klusjes_v2.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import klusjes_v2.model.*;

public interface KlusjesmanRepository extends JpaRepository<Klusjesman, Integer>{
	
	Optional<Klusjesman> findByPeople_Username(String username);

}