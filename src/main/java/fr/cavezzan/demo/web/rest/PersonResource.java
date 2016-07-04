package fr.cavezzan.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<? extends Person> list(final Pageable pageable) {
		return personService.findAll(pageable);
	}

	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	public Person showPersonForm(@PathVariable("id") String id) {
		return personService.findById(id);
	}
}
