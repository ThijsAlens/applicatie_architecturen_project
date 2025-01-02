package klusjes_v2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import klusjes_v2.model.Klus;
import klusjes_v2.services.KlusService;
import klusjes_v2.services.PeopleService;

@RestController
public class RestCtrl {
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private KlusService klusService;
	
	@PostMapping("/addKlus")
	public void addKlusje(@RequestBody Klus k) {
		klusService.addKlus(k);
	}
	
	@DeleteMapping("/REST_deleteKlus/{klusID}")
	public void deleteKlus(@PathVariable int klusId) {
		klusService.deleteKlusByKlusId(klusId);
	}
}
