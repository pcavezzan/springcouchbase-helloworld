//package fr.cavezzan.demo;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
//import org.springframework.data.couchbase.core.mapping.Document;
//import org.springframework.data.couchbase.core.mapping.Field;
//import org.springframework.data.couchbase.repository.CouchbaseRepository;
//import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
//
///**
// * This demo derived from spring-couchbase-demo.
// */
//@SpringBootApplication
//public class HelloWorld {
//
//	@EnableCouchbaseRepositories
//	@Configuration
//	static class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {
//
//		@Value("${couchbase.cluster.bucket}")
//		private String bucketName;
//
//		@Value("${couchbase.cluster.password}")
//		private String password;
//
//		@Value("${couchbase.cluster.ip}")
//		private String ip;
//
//		@Override
//		protected List<String> bootstrapHosts() {
//			return Arrays.asList(this.ip);
//		}
//
//		@Override
//		protected String getBucketName() {
//			return this.bucketName;
//		}
//
//		@Override
//		protected String getBucketPassword() {
//			return this.password;
//		}
//	}
//
//	public static void main(final String[] args) {
//		SpringApplication.run(HelloWorld.class, args).close();
//	}
//
//	@Bean
//	CommandLineRunner commandLineRunner(final PersonRepository repo) {
//		return args -> {
//			// Exemple 1
//			System.out.println(repo.findOne("person::helloworld"));
//
//			// Exemple 2
//			final Person p = new Person();
//			p.setFname("MyFirstName");
//			p.setLname("MyLastName");
//			p.setUsername("myusername");
//			p.setId("person::myusername");
//			repo.save(p);
//			repo.delete(p);
//		};
//	}
//
//}
//
//interface PersonRepository extends CouchbaseRepository<Person, String> {
//
//}
//
//@Document
//class Person {
//	@Id
//	private String id;
//	@Field
//	private String username;
//	@Field
//	private String lname;
//	@Field
//	private String fname;
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(final String username) {
//		this.username = username;
//	}
//
//	public String getFname() {
//		return fname;
//	}
//
//	public void setFname(final String fname) {
//		this.fname = fname;
//	}
//
//	public String getLname() {
//		return lname;
//	}
//
//	public void setLname(final String lname) {
//		this.lname = lname;
//	}
//
//	public void setId(final String id) {
//		this.id = id;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	@Override
//	public String toString() {
//		return "Person [id=" + id + ",username=" + username + ", lname=" + lname + ", fname=" + fname + "]";
//	}
//}
//
