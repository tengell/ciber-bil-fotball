package ciber.bil.fotball.services;

import ciber.bil.fotball.model.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
	List<Person> findAll();

	Person save(Person person);

	List<Person> save(Iterable<Person> persons);

	Optional<Person> find(long id);
	
	Optional<Person> findByFirstName(String firstName);

	void delete(long id);

	void deleteAll(Iterable<Person> personList);
}
