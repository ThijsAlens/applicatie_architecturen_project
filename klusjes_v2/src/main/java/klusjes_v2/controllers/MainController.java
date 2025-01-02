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
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;
import klusjes_v2.services.PeopleServiceImpl;

@Controller
public class MainController {
	
	@Autowired
	private PeopleServiceImpl peopleService;
	
	@GetMapping("/")
	public String index() {
		return "forward:/login";
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
