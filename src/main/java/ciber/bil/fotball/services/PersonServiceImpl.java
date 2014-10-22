package ciber.bil.fotball.services;

import ciber.bil.fotball.model.entities.Person;
import ciber.bil.fotball.repositories.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	PersonDao personDao;

	@Override
	public List<Person> findAll() {
		return (List<Person>)personDao.findAll();
	}

	@Override
	public Person save(Person person) {
		return personDao.save(person);
	}

	@Override
	public List<Person> save(Iterable<Person> persons) {
		return (List<Person>)personDao.save(persons);
	}

	@Override
	public Optional<Person> find(long id) {
		return Optional.ofNullable(personDao.findOne(id));
	}

	@Override
	public Optional<Person> findByFirstName(String firstName) {
		return Optional.ofNullable(personDao.findByFirstName(firstName));
	}

	@Override
	public void delete(long id) {
		personDao.delete(id);
	}

	@Override
	public void deleteAll(Iterable<Person> personList) {
		personDao.deleteAll();
	}
}
