package klusjes_v2.controllers;

import java.util.Optional;

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
import klusjes_v2.model.People;
import klusjes_v2.services.*;
import klusjes_v2.services.PeopleService;

@RestController
public class RestCtrl {
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private KlusService klusService;
	private KlantService KlantService;
	
	@PostMapping("/addKlus")
	public void addKlusje(@RequestBody Klus k) {
		klusService.addKlus(k);
	}
	
	@DeleteMapping("/REST_deleteKlus/{klusID}")
	public void deleteKlus(@PathVariable int klusId) {
		klusService.deleteKlusByKlusId(klusId); 
	}
	
	

    @GetMapping("/REST_getklus/{klusID}")
    public ResponseEntity<String> getKlusNameById(@PathVariable int klusID) {
        Klus klus = klusService.getKlusById(klusID); 
        if (klus != null) {
            return ResponseEntity.status(HttpStatus.OK).body(klus.getName()); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Klus not found");
        }
    }
    
    
    @GetMapping("/people/{id}")
    public ResponseEntity<People> getPeopleById(@PathVariable String id) {
        Optional<People> people = peopleService.getPeopleById(id);
        return people.map(ResponseEntity::ok)  
                .orElseGet(() -> ResponseEntity.notFound().build()); 
    }
    
    @GetMapping("/klant/{id}")
    public ResponseEntity<Klant> getKlantByUsername(@PathVariable String username) {
        Optional<Klant> klant = KlantService.getKlantByUsername(username);
        return klant.map(ResponseEntity::ok)  // If Klant found, return 200 OK with the Klant
                    .orElseGet(() -> ResponseEntity.notFound().build());  // If Klant not found, return 404 Not Found
    }
}


