package klusjes_v2.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import klusjes_v2.model.Klant;
import klusjes_v2.model.Klus;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;
import klusjes_v2.services.PeopleServiceImpl;
import klusjes_v2.services.KlantService;
import klusjes_v2.services.KlusServiceImpl;

@Controller
public class MainController {
	
	@Autowired
	private PeopleServiceImpl peopleService;
	
	@Autowired
	private KlusServiceImpl klusService;
	
	@Autowired
	private KlantService klantService;
	
	@GetMapping("/")
	public String index(HttpSession ses) {
		if (ses.getAttribute("userType") == Klant.class)
			return "forward:/klant/index";
		
		if (ses.getAttribute("userType") == Klusjesman.class)
			return "forward:/klusjesman/index";
		
		return "forward:/login_";
	}
	
	@GetMapping("/login_")
	public String login(HttpSession ses) {
		return "login";
	}
	
	@PostMapping("/login_to_index")
	public String login_to_index(Model mod, HttpSession ses, HttpServletRequest req) {
		ArrayList<People> people = peopleService.findAllPeople();
        if (people != null) {
            Boolean found = false;
            for(int i=0; i<people.size(); i++) {
                if (req.getParameter("username") == people.get(i).getUsername() && req.getParameter("password") == people.get(i).getPassword()) {
                    found = true;
                    ses.setAttribute("username", req.getParameter("username"));
                    ses.setAttribute("userType", people.get(i).getClass());
                    break;
                }
            }
            if (found) {
                return "forward:/";
            }
        }
        return "forward:register_type";

	}
	
	@GetMapping("/register_type")
	public String register() {
		return "register_type";
	}
	
	@GetMapping("/profile")
	public String profile(HttpSession ses) {
		if (ses.getAttribute("userType") == Klusjesman.class) {
			;
			ses.setAttribute("rating", rating);
		}
		return "profile";
	}
	
	/*
	 * ===========================================================================================================
	 * REST-STUFF
	 * ===========================================================================================================
	 */
	
	@GetMapping("/restAPI")
	public String restAPI(HttpSession ses) {
		ArrayList<Klus> k = klusService.getAllKlusjes();
		for (int i=0; i<k.size(); i++) {
			if (k.get(i).getKlant().getUsername() != ses.getAttribute("username")) {
				k.remove(i);
			}
		}
		
		String htmlTable = "<table border='1'>";
		if (k.size() == 0) {
			htmlTable = "Je hebt nog geen klusjes";
		} else {
			// create a string that presents the table properly
			htmlTable += "<thead>";
			htmlTable += "<tr>";
			htmlTable += "<th>Klus ID</th>";
			htmlTable += "<th>Name</th>";
			htmlTable += "<th>Prijs</th>";
			htmlTable += "<th>Beschrijving</th>";
			htmlTable += "<th>Klusjesman ID</th>";
			htmlTable += "<th>Status</th>";
			htmlTable += "</tr>";
			htmlTable += "</thead>";
			htmlTable += "<tbody>";
			
			for (Klus klus : k) {
				htmlTable += "<tr>";
				htmlTable += "<td>" + klus.getKlusId() + "</td>";
				htmlTable += "<td>" + klus.getName() + "</td>";
				htmlTable += "<td>" + klus.getPrijs() + "</td>";
				htmlTable += "<td>" + klus.getBeschrijving() + "</td>";
				htmlTable += "<td>" + klus.getKlusjesman().getKlusjesmanId() + "</td>";
				htmlTable += "<td>" + klus.getStatus() + "</td>";
				htmlTable += "</tr>";
			}	
			htmlTable += "</tbody>";
		    htmlTable += "</table>";
		}
		
		ses.setAttribute("allKlusjes", htmlTable);
		return "rest_stuff";
	}
	
	@PostMapping("/rest_stuff_add")
	public String rest_stuff_add(HttpSession ses, HttpServletRequest req) {
		RestTemplate rest = new RestTemplate();
		rest.postForObject("http://localhost:8080/REST_addKlus", new Klus(req.getAttribute("name").toString(), klantService.getKlantByUsername(ses.getAttribute("username").toString()).get(), Integer.parseInt(req.getAttribute("prijs").toString()), req.getAttribute("beschrijving").toString()), Klus.class);
		ses.setAttribute("statusAddKlus", "REST NOG DOEN!!!!!");
		return "forward:restAPI";
	}
	
	@PostMapping("/rest_stuff_delete")
	public String rest_stuff_delete(HttpSession ses, HttpServletRequest req) {
		RestTemplate rest = new RestTemplate();
		rest.delete("http://localhost:8080/REST_deleteKlus/" + req.getParameter("klus_id"));
		ses.setAttribute("statusDeleteKlus", "Deleted klus " + req.getParameter("klus_id"));
		return "forward:restAPI";
	}
	
	@PostMapping("/rest_stuff_wijs_toe")
	public String rest_stuff_wijs_toe(HttpSession ses, HttpServletRequest req) {
		RestTemplate rest = new RestTemplate();
		// send request to rest controller
		ses.setAttribute("statusWijsToe", "REST NOG DOEN!!!!!");
		return "forward:restAPI";
	}
	
	@PostMapping("/rest_stuff_beoordeel")
	public String rest_stuff_beoordeel(HttpSession ses, HttpServletRequest req) {
		RestTemplate rest = new RestTemplate();
		// send request to rest controller
		ses.setAttribute("statusBeoordeel", "REST NOG DOEN!!!!!");
		return "forward:restAPI";
	}
}
