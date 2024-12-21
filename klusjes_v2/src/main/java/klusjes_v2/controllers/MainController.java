package klusjes_v2.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import klusjes_v2.model.Klant;
import klusjes_v2.model.Klusje;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;
import klusjes_v2.model.StatusEnum;
import klusjes_v2.services.MainServiceImpl;
import klusjes_v2.services.PeopleServiceImpl;

@Controller
public class MainController {
	
	@Autowired
	private MainServiceImpl mainService;
	
	@Autowired
	private PeopleServiceImpl peopleService;
	
	@GetMapping("/")
	public String index() {
		return "forward:/login";
	}
	
	@GetMapping("/klant/index")
	public String klantIndex(HttpSession ses) {
		String html = "";
		ArrayList<Klusje> klusjes = mainService.getAllKlusjes();
		
		//select all klusjes that are created by klant
		for (int i=0; i<klusjes.size(); i++) {
			if (klusjes.get(i).getKlant().getUsername() != ses.getAttribute("username")) {
				klusjes.remove(i);
			}
		}
		//format the html to display the klusjes (if any) created by the user currently logged in
		if (klusjes == null) {
			html = html + "<p>U heeft nog geen klusjes gemaakt</p>";
		} else {
			html = html + "<p>U heeft volgende klusjes gemaakt</p>";
			for (int i=0; i<klusjes.size(); i++) {
				html = html + "<p>Klusje met ID" + Integer.toString(klusjes.get(i).getId()) + ":\t";
				html = html + klusjes.get(i).getBeschrijving() + " voor " + Double.toString(klusjes.get(i).getPrijs()) + "</p>";
				
				if (klusjes.get(i).getStatus() == StatusEnum.BESCHIKBAAR) {
					html = html + "<p>Er zijn nog geen klusjesmannen die dit klusje willen uitvoeren.</p>";
				}
				
				if (klusjes.get(i).getStatus() == StatusEnum.GEBODEN) {
					// er zijn klusjesmannen die het klusje willen doen, laat de klant deze toewijzen
					ArrayList<Klusjesman>gebondenKlusjesmannen = klusjes.get(i).getGebodenKlusjesmannen();
					for (int klusjesmanIndex=0; klusjesmanIndex<gebondenKlusjesmannen.size(); klusjesmanIndex++) {
						html = html + "<p> klusjesman met naam " + gebondenKlusjesmannen.get(klusjesmanIndex).getUsername() + "en rating" + gebondenKlusjesmannen.get(klusjesmanIndex).getRating() + "wil graag je klusje doen, klik op de knop hiernaast om deze toe te wijzen</p>";
						String key = "TOEWIJZEN__klusjeID=" + Integer.toString(klusjes.get(i).getId()) + "__klusjesmanUsername=" + gebondenKlusjesmannen.get(klusjesmanIndex).getUsername() + "__";
						html = html + "<input type=\"submit\" name=\"action\" value=\"" + key + "\"wijs deze klusjesman toe>";
					}
				}
				
				if (klusjes.get(i).getStatus() == StatusEnum.TOEGEWEZEN) {
					html = html + "<p>Dit klusje wordt verwerkt door" + klusjes.get(i).getToegewezenKlusjesman().getUsername() + "</p>";
				}
				
				if (klusjes.get(i).getStatus() == StatusEnum.UITGEVOERD) {
					html = html + "<p>Dit klusje is af. Je kan de klusjesman een rating geven:</p>";
					html = html + "<input type=\"text\" id=\"rating\" name=\"rating\" placeholder=\"Rating\">";
					String key = "RATING__klusjesmanUsername=" + klusjes.get(i).getToegewezenKlusjesman().getUsername() + "__rating=" + ;
					html = html + "<button type=\"submit\" name=\"action\" value=\"rating__" + klusjes.get(i).getToegewezenKlusjesman().getUsername() + "\">Save</button>";
				}
			}
		}
		ses.setAttribute("klusje_in_HTML", html);
		return "klant/index";
	}
	
	@GetMapping("/klusjesman/index")
	public String klusjesmanIndex() {
		return "klusjesman/index";
	}
	
	@GetMapping("/login")
	public String login(HttpSession ses) {
		if (ses.getAttribute("userType") == Klant.class) {
			return "forward:/klant/index";
		} else if (ses.getAttribute("userType") == Klusjesman.class) {
			return "forward:/klusjesman/index";
		}
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
                }
            }
            if (found) {
                return "/login";
            }
        }
        return "register";

	}
	
	@GetMapping("/register_type")
	public String register() {
		return "register_type";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "index";
	}
}
