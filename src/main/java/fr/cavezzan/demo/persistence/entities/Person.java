package fr.cavezzan.demo.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;

@Document
public class Person extends AbstractCouchbaseEntity implements fr.cavezzan.demo.api.Person {
	@Id
	private String id;
	@Field
	private String username;
	@Field
	private String lastName;
	@Field
	private String firstName;
	
	public Person() {
		setType("person");
	}
	
	public Person(fr.cavezzan.demo.api.Person person) {
		this();
		setId(person.getId());
		setFirstName(person.getFirstName());
		setLastName(person.getLastName());
		setUsername(person.getUsername());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "person [id=" + id + ",username=" + username + ", lname=" + lastName + ", fname=" + firstName + "]";
	}
}

