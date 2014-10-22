package ciber.bil.fotball.repositories;

import ciber.bil.fotball.model.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person, Long>{

	Person findByFirstName(String firstName);
}
