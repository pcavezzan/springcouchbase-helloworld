package fr.cavezzan.demo.persistence.entities.repositories;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import fr.cavezzan.demo.persistence.entities.Person;

public interface PersonRepository extends CouchbaseRepository<Person, String> {

}
