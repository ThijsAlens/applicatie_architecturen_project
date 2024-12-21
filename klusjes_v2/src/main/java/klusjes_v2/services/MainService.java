package klusjes_v2.services;

import java.util.ArrayList;

import klusjes_v2.model.Klusje;

public interface MainService {
	public ArrayList<Klusje> getAllKlusjes();
	public Klusje getKlusjeById(int id);
	public void updateKlusjeById(Klusje k);
}
