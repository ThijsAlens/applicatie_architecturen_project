package klusjes_v2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import klusjes_v2.model.Klant;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.services.MainServiceImpl;
import klusjes_v2.services.PeopleServiceImpl;

@Controller
public class PeopleController {
	
	@Autowired
	private PeopleServiceImpl peopleService;
	
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
}
