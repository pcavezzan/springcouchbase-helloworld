package fr.cavezzan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// @EnableWebMvc
// @EnableSpringDataWebSupport
@Import({SecurityConfiguration.class, CouchbaseConfiguration.class})
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
