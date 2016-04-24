package fr.cavezzan.demo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

/**
 * This demo derived from spring-couchbase-demo.
 */
@SpringBootApplication
public class HelloWorld {

	@EnableCouchbaseRepositories
	@Configuration
	static class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

		@Value("${couchbase.cluster.bucket}")
		private String bucketName;

		@Value("${couchbase.cluster.password}")
		private String password;

		@Value("${couchbase.cluster.ip}")
		private String ip;

		@Override
		protected List<String> bootstrapHosts() {
			return Arrays.asList(this.ip);
		}

		@Override
		protected String getBucketName() {
			return this.bucketName;
		}

		@Override
		protected String getBucketPassword() {
			return this.password;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloWorld.class, args).close();
	}

	@Bean
	CommandLineRunner commandLineRunner(PersonRepository repo) {
		return args -> {
			final Person p = new Person();
			p.setDob(Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			p.setFname("Hello");
			p.setLname("World");
			p.setIdentity(new Identity());
			p.getIdentity().setFname("Hello");
			p.setId("person::1");
			repo.save(p);
			System.out.println(repo.findOne(p.getId()));
			repo.delete(p);
		};
	}

}

interface PersonRepository extends CouchbaseRepository<Person, String> {

}

@Document
class Person {
	@Id
	private String id;
	@Field
	private String lname;
	@Field
	private String fname;
	@Field
	private Date dob;
	@Field
	private Identity identity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lname=" + lname + ", fname=" + fname + ", dob=" + dob + "]";
	}
}

class Identity {
	private String lname;
	private String fname;
	private LocalDate dob;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

}
