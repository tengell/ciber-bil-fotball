package ciber.bil.fotball.rest;

import ciber.bil.fotball.services.PersonService;
import ciber.bil.fotball.model.entities.Person;
import ciber.bil.fotball.util.LambdaPersonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Person> allPersons() {
		return personService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> person(@PathVariable String id) {
		Optional<Person> person = personService.find(Long.valueOf(id));

		if (person.isPresent()) {
			return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Person> savePersonAndReturnPersonWithLocationInHttpHeader(
			@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "dateOfBirth", required = false) String dateOfBirth,
			UriComponentsBuilder builder
	) {
		Person personToSave = createPerson(firstName, lastName, dateOfBirth);
		Person savedPerson = personService.save(personToSave);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/persons/{id}")
				.buildAndExpand(savedPerson.getId())
				.toUri());

		return new ResponseEntity<Person>(savedPerson, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePersonAndReturnToIndexWithAllPersons(
			@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "dateOfBirth", required = false) String dateOfBirth,
			Model model
	) {
		Person personToSave = createPerson(firstName, lastName, dateOfBirth);
		personService.save(personToSave);

		model.addAttribute("persons", allPersons());
		
		return "index";
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Person> delete(@PathVariable String id) {

	/*	try {
			personService.delete(Long.valueOf(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if(personDeleted){*/
		personService.delete(Long.valueOf(id));
		return new ResponseEntity<Person>(HttpStatus.OK);
//		}else{
//			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
//		}
	}

	private Person createPerson(String firstName, String lastName, String dateOfBirth) {
		return LambdaPersonBuilder.build(p -> {
			p.setFirstName(firstName);
			p.setLastName(lastName);
			p.setDateOfBirth(
					Optional.ofNullable(dateOfBirth)
							.map(LocalDate::parse)
			);
		});
	}
}