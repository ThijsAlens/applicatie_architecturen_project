package klusjes_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import klusjes_v2.model.People;

public interface PeopleRepository extends JpaRepository<People, String>{

}
