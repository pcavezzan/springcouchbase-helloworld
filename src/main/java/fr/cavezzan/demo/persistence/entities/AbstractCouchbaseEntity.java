package fr.cavezzan.demo.persistence.entities;

import com.couchbase.client.java.repository.annotation.Field;

public abstract class AbstractCouchbaseEntity {
	@Field
	private String type;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
