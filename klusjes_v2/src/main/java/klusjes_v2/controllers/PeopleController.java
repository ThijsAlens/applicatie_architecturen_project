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
		// name of action is normally formatted as follows: "BIND_KLUSJESMAN__klusjeID='id of klusje that needs to be changed'__klusjesmanUsername='username of klusjesman that gets it'"
		ArrayList<Klusje> klusjes = mainService.getAllKlusjes();
		String key = (String) req.getAttribute("action");
		String regex1 = "klusjeID=(.*?)__";
		Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(key);
        int klusjesID = -1;
        if (matcher1.find()) {
        	klusjesID = Integer.parseInt(matcher1.group(1));
        }
        String regex2 = "klusjesmanUsername=(.*?)__";
		Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(key);
        String klusjesmanUsername = "";
        if (matcher2.find()) {
            klusjesmanUsername = matcher2.group(1);
        }
        
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
        return "forward:/klant/index";
	}
}
