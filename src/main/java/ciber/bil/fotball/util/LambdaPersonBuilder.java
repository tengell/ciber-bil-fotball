package ciber.bil.fotball.util;

import ciber.bil.fotball.model.entities.Person;

import java.util.stream.Stream;

public class LambdaPersonBuilder {
	public static Person build(PersonSetter... personsetters){
		final Person person = new Person();
		Stream.of(personsetters).forEach(s -> s.set(person));
		return person;
	}
}

