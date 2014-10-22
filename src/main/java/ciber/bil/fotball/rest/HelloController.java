package ciber.bil.fotball.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}

//	@RequestMapping("/addPerson")
//	public String addPerson(){
//		return "addPerson";
//	}

}
