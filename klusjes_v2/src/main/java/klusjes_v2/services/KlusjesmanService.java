package klusjes_v2.services;

import java.util.ArrayList;
import java.util.Optional;

import klusjes_v2.model.Klant;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;

public interface KlusjesmanService {
	public Optional <Klusjesman> getKlusjesmanByUsername(String Username);
	public Optional<Klusjesman> getKlusjesmanById(Integer id);
	public void updateKlusjesman(Klusjesman k);
}