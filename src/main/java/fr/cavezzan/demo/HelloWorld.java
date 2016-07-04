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
