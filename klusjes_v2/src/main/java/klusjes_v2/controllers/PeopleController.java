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
import klusjes_v2.services.MainService;
import klusjes_v2.services.MainServiceImpl;
import klusjes_v2.services.PeopleServiceImpl;

@Controller
public class PeopleController {
	
	@Autowired
	private PeopleServiceImpl peopleService;
	
	@Autowired
	private MainService mainService;
	
	@PostMapping("/register_type_submit")
	public String register_type_submit(HttpServletRequest req) {
		if (req.getAttribute("isKlusjesman") == "Yes") {
			return "forward:register_klusjesman";
		} else {
			return "forward:register_klant";
		}
	}
	
	@GetMapping("/register_klusjesman")
	public String register_klusjesman(Model m) {
		m.addAttribute("newKlusjesman", new Klusjesman());
		return "register_klusjesman";
	}
	
	@GetMapping("/register_klant")
	public String register_klant(Model m) {
		m.addAttribute("newKlant", new Klant());
		return "register_klant";
	}
	
	@PostMapping("change_klusje_submit")
	public String change_klusje_submit(HttpServletRequest req, HttpSession ses) {
		String html = "";
		ArrayList<Klusje> klusjes = mainService.getAllKlusjes();
		if (klusjes == null) {
			html = html + "<p>U heeft nog geen klusjes gemaakt</p>";
		} else {
			html = html + "<p>U heeft volgende klusjes gemaakt</p>";
			for (int i=0; i<klusjes.size(); i++) {
				html = html + "<p>Klusje " + Integer.toString(i) + ":\t";
				html = html + klusjes.get(i).getBeschrijving() + " voor " + Double.toString(klusjes.get(i).getPrijs()) + "</p>";
			}
		}
		ses.setAttribute("klusje_in_HTML", html);
	}
}
