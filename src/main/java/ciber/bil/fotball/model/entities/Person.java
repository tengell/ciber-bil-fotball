package ciber.bil.fotball.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
	
	@Column
	private LocalDate dateOfBirth;

	@Column
	private int numOfGoals;

	@Column
	private int numOfYellowCards;

	@Column
	private int numOfRedCards;

	public Person() {
	}

	@Override
	public String toString() {
		return "Person{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", numOfGoals=" + numOfGoals +
				", numOfYellowCards=" + numOfYellowCards +
				", numOfRedCards=" + numOfRedCards +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Person person = (Person) o;

		if (numOfGoals != person.numOfGoals) return false;
		if (numOfRedCards != person.numOfRedCards) return false;
		if (numOfYellowCards != person.numOfYellowCards) return false;
		if (!dateOfBirth.equals(person.dateOfBirth)) return false;
		if (!firstName.equals(person.firstName)) return false;
		if (!lastName.equals(person.lastName)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
		result = 31 * result + numOfGoals;
		result = 31 * result + numOfYellowCards;
		result = 31 * result + numOfRedCards;
		return result;
	}

	public Person(String firstName, String lastName, Optional<LocalDate> dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.setDateOfBirth(dateOfBirth);
		this.numOfGoals = 0;
		this.numOfYellowCards = 0;
		this.numOfRedCards = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Optional<LocalDate> getDateOfBirth() {
		return Optional.ofNullable(dateOfBirth);
	}

	public void setDateOfBirth(Optional<LocalDate> dateOfBirth) {
		this.dateOfBirth = dateOfBirth.orElse(null);
	}

	public int getNumOfGoals() {
		return numOfGoals;
	}

	public void setNumOfGoals(int numOfGoals) {
		this.numOfGoals = numOfGoals;
	}

	public int getNumOfYellowCards() {
		return numOfYellowCards;
	}

	public void setNumOfYellowCards(int numOfYellowCards) {
		this.numOfYellowCards = numOfYellowCards;
	}

	public int getNumOfRedCards() {
		return numOfRedCards;
	}

	public void setNumOfRedCards(int numOfRedCards) {
		this.numOfRedCards = numOfRedCards;
	}
}
