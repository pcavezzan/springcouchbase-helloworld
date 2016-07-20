package fr.cavezzan.demo.persistence.entities;

import com.couchbase.client.java.repository.annotation.Field;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractCouchbaseEntity {
	@Field @JsonIgnore
	private String type;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
