package klusjes_v2.controllers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import klusjes_v2.model.StatusEnum;
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
		String key = (String) req.getAttribute("action");
		
		// select the first keyword (type of request)
        Pattern pattern = Pattern.compile("^(.*?)__");
        Matcher matcher = pattern.matcher(key);
        String type = "";
        if (matcher.find()) {
        	type = matcher.group(1);
        }
        
        switch (type) {
        case "TOEWIJZEN":
        	// user wants to give a klusje to a klusjesman
        	// read the klusjesID and klusjesmanUsername from the key
    		Pattern pattern1 = Pattern.compile("klusjeID=(.*?)__");
            Matcher matcher1 = pattern1.matcher(key);
            int klusjesID = -1;
            if (matcher1.find()) {
            	klusjesID = Integer.parseInt(matcher1.group(1));
            }
    		Pattern pattern2 = Pattern.compile("klusjesmanUsername=(.*?)__");
            Matcher matcher2 = pattern2.matcher(key);
            String klusjesmanUsername = "";
            if (matcher2.find()) {
                klusjesmanUsername = matcher2.group(1);
            }
            
            // try to addapt the database
            try {
    			Klusje klusje = mainService.getKlusjeById(klusjesID);
    			Klusjesman klusjesman = (Klusjesman) peopleService.getPeopleById(klusjesmanUsername);
    			klusje.setStatus(StatusEnum.TOEGEWEZEN);
    			klusje.setToegewezenKlusjesman(klusjesman);
    			klusje.setGebodenKlusjesmannen(null);
    			mainService.updateKlusjeById(klusje);
    		} catch (Exception e) {
    			System.out.println("In \"PeopleController.change_klusjesubmit\"Iets is misgegaan bij het toewijzen van een klusje\nGeselecteerde klusjesID = \"" + klusjesID + "\" en klusjesmanUsername = \"" + klusjesmanUsername + "\"");
    			e.printStackTrace();
    		}
            break;
            
        case "RATING":
        	// user wants to input a rating for the klusjesman
        	// read the klusjesmanUsername from the key
        	Pattern pattern3 = Pattern.compile("klusjeID=(.*?)__");
            Matcher matcher3 = pattern3.matcher(key);
            int klusjeID3 = -1;
            if (matcher3.find()) {
            	klusjeID3 = Integer.parseInt(matcher3.group(1));
            }
            Pattern pattern4 = Pattern.compile("rating=(.*?)__");
            Matcher matcher4 = pattern4.matcher(key);
            float rating = -1;
            if (matcher4.find()) {
            	rating = Float.parseFloat(matcher4.group(1));
            }
            
            // try to addapt the database
            try {
    			Klusje klusje= mainService.getKlusjeById(klusjeID3);
    			klusje.setRating(rating);
    			mainService.updateKlusjeById(klusje);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            break;
        }
        
        return "forward:/klant/index";
	}
}
