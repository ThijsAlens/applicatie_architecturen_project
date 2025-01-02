package klusjes_v2.services;

import java.util.ArrayList;

import klusjes_v2.model.Klus;
import klusjes_v2.model.Klusjesman;



public interface KlusService {
	public ArrayList<Klus> getAllKlusjes();
	public Klus getKlusjeById(int id);
	public void updateKlusje(Klus k);
	public void addKlus(Klus k);
	public void deleteBiedingenByKlusId(int id);
	public ArrayList<Klusjesman> getGebodenKlusjesmannenByKlusId(int klusId);
	public void setGebodenKlusjesmannenByKlusId(int klusId, ArrayList<Klusjesman> km);
}
