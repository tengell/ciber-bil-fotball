package ciber.bil.fotball.util;

import ciber.bil.fotball.model.entities.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonBuilderTest {
	
	@Test
	public void canConstructPersonWithOldBuilder(){
		Person person = PersonBuilder.person().withFirstName("Julius").withLastName("Aasflenge").build();

		assertEquals(person.getFirstName(), "Julius");
		assertEquals(person.getLastName(), "Aasflenge");
	}
}