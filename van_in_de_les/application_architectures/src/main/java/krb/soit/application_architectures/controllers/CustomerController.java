package krb.soit.application_architectures.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import krb.soit.application_architectures.model.Costumer;
import krb.soit.application_architectures.services.CustomerService;
import krb.soit.application_architectures.services.CustomerServiceImpl;
import krb.soit.application_architectures.services.MainService;
import krb.soit.application_architectures.services.MainServiceImpl;
import krb.soit.application_architectures.services.RentService;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl costumerServiceImpl;
	
	@PostMapping("/register_to_index")
	public String register(Model mod, HttpSession ses, HttpServletRequest req, @ModelAttribute("newCostumer") @Valid Costumer costumer, Errors e) {
		if (e.hasErrors())
			return "register";
		
		ArrayList<Costumer> res = costumerServiceImpl.findAllCostumers();
		if (res != null) {
			Boolean found = false;
			for(int i=0; i<res.size(); i++) {
				if (req.getParameter("email") == res.get(i).getEmail() && req.getParameter("password") == res.get(i).getPassword()) {
					found = true;
				}
			}
			if (found) {
				ses.setAttribute("email", req.getParameter("email"));
				return "index";
			}
		}
		costumerServiceImpl.addCostumer(costumer);
		ses.setAttribute("email", req.getParameter("email"));
		return "index";
	}
}
