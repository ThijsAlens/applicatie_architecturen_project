package klusjes_v2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import klusjes_v2.model.Klusje;
import klusjes_v2.model.People;

public interface KlusjesRepository extends JpaRepository<Klusje, Integer>{

}
