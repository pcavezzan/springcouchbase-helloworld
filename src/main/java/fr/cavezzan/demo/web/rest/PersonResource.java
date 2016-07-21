package fr.cavezzan.demo.web.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.cavezzan.demo.api.Person;
import fr.cavezzan.demo.api.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonResource {

	@Autowired
	private PersonService personService;

	@RequestMapping(path="/list", method = RequestMethod.GET)
	public Iterable<? extends Person> list(final Pageable pageable) {
		return personService.findAll(pageable);
	}

	@RequestMapping(value={"/{id}", "/"}, method = RequestMethod.GET)
	public Person showPersonForm(@PathVariable("id") Optional<String> id) {
		Person p = new fr.cavezzan.demo.persistence.entities.Person();
		if (id.isPresent()) {
			p = personService.findById(id.get());			
		}
		return p;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public Person createPersonForm(Person person) {
		return personService.save(person);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.PUT)
	public Person updatePersonForm(@PathVariable final String id, @RequestBody fr.cavezzan.demo.persistence.entities.Person person) {
		return personService.save(person);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	public void deletePersonForm(@PathVariable final String id) {
		personService.delete(id);
	}
}
