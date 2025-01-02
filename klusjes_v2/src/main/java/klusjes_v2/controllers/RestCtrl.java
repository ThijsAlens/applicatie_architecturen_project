package klusjes_v2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import klusjes_v2.model.Klant;
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
	
	
    // New GET endpoint to retrieve the name of a Klus by its ID
    @GetMapping("/klus/{klusID}/name")
    public ResponseEntity<String> getKlusNameById(@PathVariable int klusID) {
        Klus klus = klusService.getKlusById(klusID); // Assuming getKlusById returns the full Klus object
        if (klus != null) {
            return ResponseEntity.status(HttpStatus.OK).body(klus.getName()); // Return the name of the Klus
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Klus not found");
        }
    }
}

}
