package ciber.bil.fotball.rest;

import ciber.bil.fotball.model.entities.Person;
import ciber.bil.fotball.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@Autowired
	PersonService personService;

	@RequestMapping("/")
	public String index(Model model){
		Iterable<Person> personList = personService.findAll();
		model.addAttribute("persons", personList);
		return "index";
	}

}
