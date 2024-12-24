package klusjes_v2.services;

import java.util.ArrayList;

import klusjes_v2.model.Klus;



public interface MainService {
	public ArrayList<Klus> getAllKlusjes();
	public Klus getKlusjeById(int id);
	public void updateKlusjeById(Klus k);
}
