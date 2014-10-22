package ciber.bil.fotball.util;

import ciber.bil.fotball.model.entities.Person;

@FunctionalInterface
public interface PersonSetter{
	public void set(Person person);
}
