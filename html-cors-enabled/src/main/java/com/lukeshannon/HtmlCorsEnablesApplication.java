package com.lukeshannon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class HtmlCorsEnablesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtmlCorsEnablesApplication.class, args);
	}
}

class Person {
	private String name;
	private int age;
	
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
}

@RestController
class PersonEndPoint {
	
	@RequestMapping("/persons")
	@CrossOrigin
	public ResponseEntity<List<Person>> person() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Dude", 48));
		persons.add(new Person("Maude", 35));
		persons.add(new Person("Walter", 58));
		return ResponseEntity.ok(persons);
	}
}

@Configuration
class MyConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
                registry.addMapping("/persons");
            }
        };
    }
}
