package fr.cavezzan.demo.api;

import org.springframework.data.domain.Pageable;

public interface PersonService {
	
	Iterable<? extends Person> findAll(Pageable pageable);
	
	Person findById(String id);
	
	Person save(Person person);
	
	void delete(Person person);
}
