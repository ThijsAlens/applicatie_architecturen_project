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
		if (klusjes == null) {
			html = html + "<p>U heeft nog geen klusjes gemaakt</p>";
		} else {
			html = html + "<p>U heeft volgende klusjes gemaakt</p>";
			for (int i=0; i<klusjes.size(); i++) {
				html = html + "<p>Klusje met ID" + Integer.toString(klusjes.get(i).getId()) + ":\t";
				if (klusjes.get(i).getStatus() == StatusEnum.BESCHIKBAAR) {
					html = html + klusjes.get(i).getBeschrijving() + " voor " + Double.toString(klusjes.get(i).getPrijs()) + "</p>";
					ArrayList<Klusjesman>gebondenKlusjesmannen = klusjes.get(i).getGebodenKlusjesmannen();
					if (gebondenKlusjesmannen == null) {
						html = html + "<p>Er zijn nog geen klusjesmannen die dit klusje willen uitvoeren</p>";
					} else {
						for (int klusjesmanIndex=0; klusjesmanIndex<gebondenKlusjesmannen.size(); klusjesmanIndex++) {
							html = html + "<p> klusjesman met naam " + gebondenKlusjesmannen.get(klusjesmanIndex).getUsername() + "wil graag je klusje doen, klik op de knop hiernaast om deze toe te wijzen</p>";
							html = html + "<input type=\"submit\" name=\"action\" value=\"BIND_KLUSJESMAN__klusjeID=" + Integer.toString(klusjes.get(i).getId()) + "__klusjesmanUsername=" + gebondenKlusjesmannen.get(klusjesmanIndex).getUsername() + "__\"wijs deze klusjesman toe>";
						}
					}
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
