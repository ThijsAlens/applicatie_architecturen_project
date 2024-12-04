package krb.soit.application_architectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import krb.soit.application_architectures.model.Rent;
import krb.soit.application_architectures.services.RentService;
import krb.soit.application_architectures.services.RentServiceImpl;

@Controller
public class RentController {
	@Autowired
	private RentServiceImpl rentService;
	
	@GetMapping("/rental")
	public String rental(Model m, HttpServletRequest req) {
		m.addAttribute("rent_price", rentService.berekenPrijs(Integer.parseInt(req.getParameter("days")), 1000));
		return "rental";
	}
	
	@PostMapping("/rental_again")
	public String rental_again(Model mod, HttpServletRequest req) {
		mod.addAttribute("newRent", new Rent());
		mod.addAttribute("rent_price", rentService.berekenPrijs(Integer.parseInt(req.getParameter("days")), 1000));
		return "rental";
	}
}
