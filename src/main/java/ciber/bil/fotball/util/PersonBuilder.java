package ciber.bil.fotball.util;

import ciber.bil.fotball.model.entities.Person;

import java.time.LocalDate;
import java.util.Optional;

public class PersonBuilder {
	
	private final Person person;
	
	private PersonBuilder(){
		this.person = new Person();
	}
	
	public static PersonBuilder person() {
		return new PersonBuilder();
	}
	
	public PersonBuilder withFirstName(final String firstName){
		this.person.setFirstName(firstName);
		return this;
	}

	public PersonBuilder withLastName(final String lastName){
		this.person.setLastName(lastName);
		return this;
	}
	
	public PersonBuilder withDateOfBirth(final Optional<LocalDate> dateOfBirth){
		this.person.setDateOfBirth(dateOfBirth);
		return this;
	}
	
	public Person build(){
		return this.person;
	}
}
