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
import klusjes_v2.model.Klus;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;
import klusjes_v2.model.StatusEnum;
import klusjes_v2.services.KlantService;
import klusjes_v2.services.KlusService;
import klusjes_v2.services.PeopleServiceImpl;

@Controller
public class PeopleController {
	
	@Autowired
	private PeopleServiceImpl peopleService;
	
	@Autowired
	private KlusService klusService;
	
	/*
	 * ===========================================================================================================
	 * functies voor het correct doorsturen van de gebruiker afhankelijk van het type (klant of klusjesman)
	 * ===========================================================================================================
	*/
	@PostMapping("/register_type_submit")
	public String register_type_submit(HttpServletRequest req) {
		System.out.println("req=" + req.getAttribute("isKlusjesman"));
		if (req.getAttribute("isKlusjesman") == "Yes") {
			return "forward:register_klusjesman";
		} else {
			return "forward:register_klant";
		}
	}
	
	@GetMapping("/register_klusjesman")
	public String register_klusjesman(Model m) {
		m.addAttribute("newKlusjesman", new Klusjesman(new People()));
		return "register_klusjesman";
	}
	
	@GetMapping("/register_klant")
	public String register_klant(Model m) {
		m.addAttribute("newKlant", new Klant(new People()));
		return "register_klant";
	}
	
	/*
	 * ===========================================================================================================
	 * functies voor de klantenzijde:
	 * 		laden van de indexpagina met alle mogelijke opties
	 * 		verwerken van acties gedaan op de indexpagina
	 * ===========================================================================================================
	 */
	
	@GetMapping("/klant/index")
	public String klantIndex(HttpSession ses) {
		String html = "";
		ArrayList<Klus> klusjes = klusService.getAllKlusjes();
		
		//select all klusjes that are created by klant
		for (int i=0; i<klusjes.size(); i++) {
			if (klusjes.get(i).getKlant().getUsername() != ses.getAttribute("username")) {
				klusjes.remove(i);
			}
		}
		//format the html to display the klusjes (if any) created by the user currently logged in
		if (klusjes.size() == 0) {
			html = html + "<p>U heeft nog geen klusjes gemaakt</p>";
		} else {
			html = html + "<p>U heeft volgende klusjes gemaakt</p>";
			for (int i=0; i<klusjes.size(); i++) {
				html = html + "<p>Klusje met ID" + Integer.toString(klusjes.get(i).getKlusId()) + ":\t";
				html = html + klusjes.get(i).getBeschrijving() + " voor " + Double.toString(klusjes.get(i).getPrijs()) + "</p>";
				
				switch (klusjes.get(i).getStatus()) {
				case BESCHIKBAAR:
					html = html + "<p>Er zijn nog geen klusjesmannen die dit klusje willen uitvoeren.</p>";
					break;
				
				case GEBODEN:
					// er zijn klusjesmannen die het klusje willen doen, laat de klant deze toewijzen
					ArrayList<Klusjesman>gebondenKlusjesmannen = klusService.getGebodenKlusjesmannenByKlusId(klusjes.get(i).getKlusId());
					for (int klusjesmanIndex=0; klusjesmanIndex<gebondenKlusjesmannen.size(); klusjesmanIndex++) {
						html = html + "<p> klusjesman met naam " + gebondenKlusjesmannen.get(klusjesmanIndex).getPeople().getUsername() + "en rating" + gebondenKlusjesmannen.get(klusjesmanIndex).getRating() + "wil graag je klusje doen, klik op de knop hiernaast om deze toe te wijzen</p>";
						String key = "TOEWIJZEN__klusjeID=" + Integer.toString(klusjes.get(i).getKlusId()) + "__klusjesmanUsername=" + gebondenKlusjesmannen.get(klusjesmanIndex).getPeople().getUsername() + "__";
						html = html + "<input type=\"submit\" name=\"action\" value=\"" + key + "\"wijs deze klusjesman toe>";
					}
					break;
					
				case TOEGEWEZEN:
					html = html + "<p>Dit klusje wordt verwerkt door" + klusjes.get(i).getKlusjesman().getPeople().getUsername() + "</p>";
					break;
				
				case UITGEVOERD:
					html = html + "<p>Dit klusje is af. Je kan de klusjesman een rating geven:</p>";
					html = html + "<input type=\"text\" id=\"rating\" name=\"rating\" placeholder=\"Rating\">";
					String key = "RATING__klusjesmanUsername=" + klusjes.get(i).getKlusjesman().getPeople().getUsername();
					html = html + "<button type=\"submit\" name=\"action\" value=\"" + key + "\">Bewaar</button>";
					break;
				
				case BEOORDEELD:
					// moet niet meer weergeven worden
					break;
				}
					
			}
		}
		ses.setAttribute("klusje_in_HTML", html);
		return "klant/index";
	}
	
	@PostMapping("/nieuw_klusje")
	public String nieuw_klusje(HttpServletRequest req, HttpSession ses) {
		Klus k = new Klus(req.getAttribute("name").toString(), KlantService.getKlantByUsername(ses.getAttribute("username").toString()), Integer.parseInt(req.getAttribute("prijs").toString()), req.getAttribute("beschrijving").toString());
		klusService.addKlus(k);
		return "forward:/klant/index";
	}
	
	@PostMapping("/change_klusje_submit")
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
    			Klus klusje = klusService.getKlusById(klusjesID);
    			Klusjesman klusjesman = (Klusjesman) peopleService.getKlusjesmanById(klusjesmanUsername);
    			klusje.setStatus(StatusEnum.TOEGEWEZEN);
    			klusje.setKlusjesman(klusjesman);
    			klusService.deleteBiedingenByKlusId(klusje.getKlusId());
    			klusService.updateKlusje(klusje);
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
            int rating = (int) req.getAttribute("rating");
            
            // try to addapt the database
            try {
    			Klus klusje= klusService.getKlusById(klusjeID3);
    			klusje.setRating(rating);
    			klusService.updateKlusje(klusje);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            break;
        }
        
        return "forward:/klant/index";
	}
	
	/*
	 * ===========================================================================================================
	 * functies voor de klusjesman zijde:
	 * 		laden van de indexpagina met alle mogelijke opties
	 * 		verwerken van acties op de indexpagina
	 * ===========================================================================================================
	 */
	
	@GetMapping("/klusjesman/index")
	public String klusjesmanIndex(HttpSession ses) {
		String html = "";
		Klusjesman klusjesman = peopleService.getKlusjesmanById(ses.getAttribute("username").toString());
		ArrayList<Klus> klusjes = klusService.getAllKlusjes();
		
		//select all klusjes that are created by klusjesman
		for (int i=0; i<klusjes.size(); i++) {
			if (klusjes.get(i).getKlusjesman().getKlusjesmanId() != ses.getAttribute("username") && klusjes.get(i).getStatus() != StatusEnum.TOEGEWEZEN) {
				klusjes.remove(i);
			}
		}
		
		if (klusjes.size() == 0) {
			html = html + "<p>U heeft nog geen klusjes toegewezen gekregen</p>";
		} else {
			for (int i=0; i<klusjes.size(); i++) {
				html = html + "<p>Klusje met ID" + Integer.toString(klusjes.get(i).getKlusId()) + "\t:";
				html = html + klusjes.get(i).getBeschrijving() + " voor " + Double.toString(klusjes.get(i).getPrijs()) + "</p>";
				String key = "UITVOEREN__klusjeID=" + klusjes.get(i).getKlusId() + "__";
				html = html + "<input type=\"submit\" name=\"action\" value=\"" + key + "\"Het klusje is uitgevoerd>";
			}
		}
		
		html = html + "<h2>Op volgende klusjes kan je nog bieden</h2>";
		klusjes = klusService.getAllKlusjes();
		
		// select all klusjes that are to be toegewezen
		for (int i=0; i<klusjes.size(); i++) {
			if (klusjes.get(i).getStatus() != StatusEnum.GEBODEN) {
				klusjes.remove(i);
			}
		}
		
		if (klusjes.size() == 0) {
			html = html + "Er zijn geen klusjes waarop je kan bieden of hebt geboden.";
		}
		for (int i=0; i<klusjes.size(); i++) {
			ArrayList<Klusjesman> klusjesmannen = klusService.getGebodenKlusjesmannenByKlusId(klusjes.get(i).getKlusId());
			for (int j=0; j<klusjesmannen.size(); j++) {
				if (klusjesmannen.get(i).getKlusjesmanId() == ses.getAttribute("username")) {
					html = html + "<p>U bood reeds op klusje met ID" + Integer.toString(klusjes.get(i).getKlusId()) + ":\t";
					html = html + klusjes.get(i).getBeschrijving() + " voor " + Double.toString(klusjes.get(i).getPrijs()) + "</p>";
					String key = "BIEDEN_INTREKKEN__klusjesmanID=" + Integer.toString(klusjesman.getKlusjesmanId()) + "__klusjeID=" + klusjes.get(i).getKlusId() + "__";
					html = html + "<input type=\"submit\" name=\"action\" value=\"" + key + "\"trek uw bieding in>";
				} else {
					html = html + "<p>Klusje met ID" + Integer.toString(klusjes.get(i).getKlusId()) + ":\t";
					html = html + klusjes.get(i).getBeschrijving() + " voor " + Double.toString(klusjes.get(i).getPrijs()) + "</p>";
					String key = "BIEDEN_TOEVOEGEN__klusjesmanID=" + Integer.toString(klusjesman.getKlusjesmanId()) + "__klusjeID=" + klusjes.get(i).getKlusId() + "__";
					html = html + "<input type=\"submit\" name=\"action\" value=\"" + key + "\"biedt op dit klusje>";
				}
			}
		}
		
		ses.setAttribute("openstaande_klusjes", html);
		return "klusjesman/index";
	}
	
	@PostMapping("change_openstaande_klusjes_submit")
	public String change_openstaande_klusjes_submit(HttpServletRequest req) {
		String key = (String) req.getAttribute("action");
		
		// select the first keyword (type of request)
        Pattern pattern = Pattern.compile("^(.*?)__");
        Matcher matcher = pattern.matcher(key);
        String type = "";
        if (matcher.find()) {
        	type = matcher.group(1);
        }
        
        switch (type) {
        case "UITVOEREN":
        	// user wants to set a klusje to uitgevoerd
        	// read the klusjesID from the key
    		Pattern pattern1 = Pattern.compile("klusjeID=(.*?)__");
            Matcher matcher1 = pattern1.matcher(key);
            int klusjesID = -1;
            if (matcher1.find()) {
            	klusjesID = Integer.parseInt(matcher1.group(1));
            }
            
            // try to addapt the database
    		try {
    			Klus k = klusService.getKlusById(klusjesID);
    			k.setStatus(StatusEnum.UITGEVOERD);
    			klusService.updateKlusje(k);
    		} catch (Exception e) {
            	e.printStackTrace();
            }
            break;
            
        case "BIEDEN_INTREKKEN":
        	// user wants to delete its bieding
        	// read the klusjesID and klusjesmanUsername from the key
    		Pattern pattern2 = Pattern.compile("klusjeID=(.*?)__");
            Matcher matcher2 = pattern2.matcher(key);
            int klusjesID2 = -1;
            if (matcher2.find()) {
            	klusjesID2 = Integer.parseInt(matcher2.group(1));
            }
    		Pattern pattern3 = Pattern.compile("klusjesmanUsername=(.*?)__");
            Matcher matcher3 = pattern3.matcher(key);
            String klusjesmanUsername3 = "";
            if (matcher3.find()) {
                klusjesmanUsername3 = matcher3.group(1);
            }
            
            // try to addapt the database
            try {
            	Klus k = klusService.getKlusById(klusjesID2);
            	ArrayList<Klusjesman> km = klusService.getGebodenKlusjesmannenByKlusId(k.getKlusId());
            	for (int i=0; i<km.size(); i++) {
            		if (km.get(i).getPeople().getUsername() == klusjesmanUsername3) {
            			km.remove(i);
            		}
            	}
            	klusService.setGebodenKlusjesmannenByKlusId(k.getKlusId(), km);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            break;
        
        case "BIEDEN_TOEVOEGEN":
        	// user wants to biedt on a klusje
        	// read the klusjesID and klusjesmanUsername from the key
    		Pattern pattern4 = Pattern.compile("klusjeID=(.*?)__");
            Matcher matcher4 = pattern4.matcher(key);
            int klusjesID4 = -1;
            if (matcher4.find()) {
            	klusjesID4 = Integer.parseInt(matcher4.group(1));
            }
    		Pattern pattern5 = Pattern.compile("klusjesmanUsername=(.*?)__");
            Matcher matcher5 = pattern5.matcher(key);
            String klusjesmanUsername5 = "";
            if (matcher5.find()) {
                klusjesmanUsername5 = matcher5.group(1);
            }
            
            // try to addapt the database
            try {
            	Klus k = klusService.getKlusById(klusjesID4);
            	ArrayList<Klusjesman> klusjesmannen = klusService.getGebodenKlusjesmannenByKlusId(k.getKlusId());
            	klusjesmannen.add(peopleService.getKlusjesmanById(klusjesmanUsername5));
            	klusService.setGebodenKlusjesmannenByKlusId(k.getKlusId(), klusjesmannen);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            break;
        }
        	
		return "forward:/klusjesman/index";
	}
}
