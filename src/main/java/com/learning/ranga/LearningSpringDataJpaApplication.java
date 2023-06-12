package com.learning.ranga;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.ranga.entity.Contact;
import com.learning.ranga.entity.Gender;
import com.learning.ranga.entity.Person;
import com.learning.ranga.entity.PhoneType;
import com.learning.ranga.entity.Vehicle;
import com.learning.ranga.repository.PersonRepository;

@SpringBootApplication
public class LearningSpringDataJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringDataJpaApplication.class, args);
	}

	@Autowired
	PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("** Spring Data JPA **");

		Person person = new Person();
		person.setFirstName("Ranga");
		person.setLastName("G");
		person.setEmail("ranga.g@test.com");
		person.setGender(Gender.MALE);
		person.setPanNumber("AAAAA1234A");
		person.setDob(Date.valueOf("1984-03-22"));
		person.setCreditCardNumber("1111-1111-1111-1111");
		
		person.getNickNames().add("user1");
		person.getNickNames().add("user2");
		person.getNickNames().add("user3");

		Contact contact = new Contact();
		contact.setMobile("9999911111");
		contact.setHome("1234567890");
		person.setSelf(contact);
		
		Vehicle tW = new Vehicle();
		tW.setLicense("IN 00 EF 0000");
		tW.setType("2W");
		person.getVehicles().add(tW);

		person.getPhoneNumbers().put(PhoneType.MOBILE, "9988774455");
		
		Person savedPerson = personRepository.save(person);

		System.out.println("Saved :: " + savedPerson);

		savedPerson.setAge(40);

		Person updatedPerson = personRepository.save(savedPerson);

		System.out.println("Updated :: " + updatedPerson);

		Optional<Person> findById = personRepository.findById(100L);
		System.out.println("Get by ID :: " + findById);
	}

}
