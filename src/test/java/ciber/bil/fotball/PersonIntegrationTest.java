package ciber.bil.fotball;

import ciber.bil.fotball.model.entities.Person;
import ciber.bil.fotball.rest.PersonController;
import ciber.bil.fotball.services.PersonService;
import ciber.bil.fotball.util.LambdaPersonBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PersonIntegrationTest {

	private MockMvc mockMvc;
	private List<Person> personList = new ArrayList<Person>();

	@InjectMocks
	PersonController personController;

	@Mock
	PersonService personService;

	long nonExistingId = 666L;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(personController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
		personList.clear();
		fillPersonList();
	}

	private void fillPersonList() {
		personList.add(
				LambdaPersonBuilder.build(p -> {
					p.setId(1L);
					p.setFirstName("Julius");
					p.setLastName("Aasflenge");
				}));

		personList.add(
				LambdaPersonBuilder.build(p -> {
					p.setId(2L);
					p.setFirstName("Edwin");
					p.setLastName("Rainden");
				}));

		personList.add(
				LambdaPersonBuilder.build(p -> {
					p.setId(3L);
					p.setFirstName("Phil");
					p.setLastName("Yates");
				}));
	}


	@Test
	public void getReturns200AndPersonListInJson() throws Exception {
		when(personService.findAll()).thenReturn(personList);

		this.mockMvc.perform(
				get("/persons")
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk());
	}

	@Test
	public void postCreatesPersonAndReturns201AndLocationInHeader() throws Exception {
		Person person = LambdaPersonBuilder.build(p -> {
			p.setId(4L);
			p.setFirstName("John");
			p.setLastName("Insert");
			p.setDateOfBirth(Optional.empty()); //Must be set in test
		});

		when(personService.save(person)).thenReturn(person);

		this.mockMvc.perform(
				post("/persons")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.param("firstName", person.getFirstName())
						.param("lastName", person.getLastName())
		)
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", Matchers.endsWith("/persons/4")));
	}

	@Test
	public void getReturnsPersonInJsonAnd200IfFound() throws Exception {
		long idOfExistingPerson = 1L;
		when(personService.find(anyLong())).
				thenReturn(personList.stream().filter(p -> p.getId() == idOfExistingPerson).findFirst());

		this.mockMvc.perform(
				get("/persons/{id}", idOfExistingPerson)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.firstName").value("Julius"))
				.andExpect(jsonPath("$.lastName").value("Aasflenge"))
				.andExpect(status().isOk());
	}

	@Test
	public void getReturns404IfPersonNotFound() throws Exception {
		when(personService.find(anyLong())).thenReturn(Optional.empty());

		this.mockMvc.perform(
				get("/persons/{id}", nonExistingId)
		).andExpect(status().isNotFound());
	}

//	@Test
//	public void deleteReturns200IfPersonIsDeleted() throws Exception {
//		when(personService.delete(anyLong())).thenReturn(true);
//		
//		this.mockMvc.perform(
//				delete("/persons/{id}", 1L)
//		).andExpect(status().isOk());
//		
//		verify(personService).delete(1L);
//	}
//
//	@Test
//	public void deleteReturns404WhenPersonNotFound() throws Exception {
//		when(personService.delete(anyLong())).thenReturn(false);
//		
//		this.mockMvc.perform(
//				delete("/persons/{id}", nonExistingId)
//		).andExpect(status().isNotFound());
//
//		verify(personService).delete(nonExistingId);
//	}

	//TODO If something goes wrong while deleting -> Forbidden status

}
