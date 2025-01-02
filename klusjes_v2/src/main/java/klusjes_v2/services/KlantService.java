package klusjes_v2.services;

import java.util.ArrayList;
import java.util.Optional;

import klusjes_v2.model.Klant;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;

public interface KlantService {
	public Optional <Klant> getKlantByUsername(String Username);
	public Optional<Klant> getKlantById(Integer id);
}