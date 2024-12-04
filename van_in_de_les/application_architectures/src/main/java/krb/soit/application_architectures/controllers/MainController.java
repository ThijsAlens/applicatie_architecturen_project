package krb.soit.application_architectures.controllers;
import krb.soit.application_architectures.model.*;
import krb.soit.application_architectures.services.MainServiceImpl;
import krb.soit.application_architectures.services.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	private ServletContext ctx;
	
	@Autowired
	private MainServiceImpl mainService;
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@PostConstruct
	public void init_model() {		
		// ctx.setAttribute("data", new Data());
		ctx.setAttribute("allCars", mainService.findAllCars());
		ctx.setAttribute("allLocations", mainService.findAllLocations());
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login.html")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("newCostumer", new Costumer());
		return "register"; 
	}
	
	@GetMapping("/rental.html")
	public String rental(HttpSession ses, Model mod) {
		if (ses.getAttribute("email") != null) {
			mod.addAttribute("newRent", new Rent());
			return "rental";
		} else
			return "forward:/register";
	}
	
	@GetMapping("/overview.html")
	public String overview(HttpSession ses, Model mod) {
		if (ses.getAttribute("email") != null)
			return "overview";
		else
			return "forward:/register";
	}
	
	@GetMapping("/logout.html")
	public String logout(HttpSession ses) {
		ses.invalidate();
		return "index";
	}
	
	@PostMapping("/login_to_index")
	public String login_to_index(Model mod, HttpSession ses, HttpServletRequest req) {
		ArrayList<Costumer> res = customerService.findAllCostumers();
		if (res != null) {
			Boolean found = false;
			for(int i=0; i<res.size(); i++) {
				if (req.getParameter("email") == res.get(i).getEmail() && req.getParameter("password") == res.get(i).getPassword()) {
					found = true;
				}
			}
			if (found) {
				return "index";
			}
		}
		return "index.html";
	}
	
	@GetMapping("/carstuff.html")
	public String carstuff() {
		return "carstuff";
	}
	
	@PostMapping("/carstuff_delete")
	public String carstuff_delete(Model mod, HttpSession ses, HttpServletRequest req) {
		RestTemplate rest = new RestTemplate();
		rest.delete("http://localhost:8080/deleteCar/" + req.getParameter("carID_del"));
		ses.setAttribute("statusDeletedCar", "Deleted " + req.getParameter("carID_del"));
		return "carstuff";
	}
	
	@PostMapping("/carstuff_show")
	public String carstuff_add(Model mod, HttpSession ses, HttpServletRequest req) {
		RestTemplate rest = new RestTemplate();
		List res = rest.getForObject("http://localhost:8080/cars", List.class);
		ses.setAttribute("statusShowCars", res.toString());
		return "carstuff";
	}
	
}
