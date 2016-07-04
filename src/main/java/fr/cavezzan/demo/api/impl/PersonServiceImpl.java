package fr.cavezzan.demo.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cavezzan.demo.api.Person;
import fr.cavezzan.demo.api.PersonService;
import fr.cavezzan.demo.persistence.entities.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Iterable<? extends Person> findAll(Pageable pageable) {
		return personRepository.findAll();
	}

	@Override
	public Person findById(String id) {
		return personRepository.findOne("person::" + id);
	}

	@Override
	public Person save(Person person) {
		final fr.cavezzan.demo.persistence.entities.Person couchbasePerson = new fr.cavezzan.demo.persistence.entities.Person(person);
		if (couchbasePerson.getId() == null) {
			couchbasePerson.setId("person::" + couchbasePerson.getUsername());
		}
		return personRepository.save(couchbasePerson);
	}

	@Override
	public void delete(Person person) {
		personRepository.delete(person.getId());
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public PersonRepository getPersonRepository() {
		return personRepository;
	}
}
