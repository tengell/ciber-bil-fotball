package ciber.bil.fotball;

import ciber.bil.fotball.model.entities.Person;
import ciber.bil.fotball.services.PersonService;
import ciber.bil.fotball.util.LambdaPersonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static ciber.bil.fotball.JPAAssertions.assertTableExists;
import static ciber.bil.fotball.JPAAssertions.assertTableHasColumn;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PersonMappingIntegrationTest {

	@Autowired
	EntityManager manager;
	
	@Autowired
	PersonService personService;

	@Test
	public void tablesExists() throws Exception{
		assertTableExists(manager, "PERSON");
	}
	
	@Test
	public void tableHasColumns() throws Exception{
		assertTableHasColumn(manager, "PERSON", "first_name");
		assertTableHasColumn(manager, "PERSON", "last_name");
	}
	
	@Test
	public void findRetrievesPersonFromRepository(){
		assert(personService.findByFirstName("Ole").isPresent());
	}
	
	@Test
	public void saveStoresPersonInRepository(){
		Person halvard = LambdaPersonBuilder.build(p -> {
			p.setFirstName("Halvard");
			p.setLastName("Slipesten");
		});
		
		long id = personService.save(halvard).getId();
		
		assert(personService.findAll().size() == 1);
		assert(personService.find(id).isPresent());
	}
	
	@Test
	public void deleteRemovesPersonFromRepository(){
		Person guymon = LambdaPersonBuilder.build(p -> {
			p.setFirstName("Guymon");
			p.setLastName("Smith");
		});
		
		long id = personService.save(guymon).getId();

		assert(personService.findAll().size() == 1);
		personService.delete(id);
		assert(personService.findAll().size() == 0);
	}
}
