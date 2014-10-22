package ciber.bil.fotball.util;

import ciber.bil.fotball.model.entities.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class LambdaPersonBuilderTest {
	
	@Test
	public void canConstructPersonWithNewLambdaPersonBulder(){
		Person person = LambdaPersonBuilder.build(p ->
		{
			p.setFirstName("Julius");
			p.setLastName("Aasflenge");
		});
		
		assertEquals(person.getFirstName(), "Julius");
		assertEquals(person.getLastName(), "Aasflenge");
	}
}