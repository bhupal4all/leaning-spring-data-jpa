package com.learning.ranga;

import java.sql.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.ranga.entity.Employement;
import com.learning.ranga.entity.Gender;
import com.learning.ranga.entity.Person;
import com.learning.ranga.repository.EmployementRepository;
import com.learning.ranga.repository.PersonRepository;

@SpringBootApplication
public class LearningSpringDataJpaApplication implements CommandLineRunner {

	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringDataJpaApplication.class, args);
	}

	@Autowired
	PersonRepository personRepository;

	@Autowired
	EmployementRepository employementRepository;

	@Override
	public void run(String... args) throws Exception {

		log.debug("** Spring Data JPA **");

		Person person = createPersonObject();
		Employement work = new Employement("Company", "COMP001", "BLR", "KA");
		person.setWork(work);

		// Save Person
		Person savedPerson = personRepository.save(person);

		// Print Person
		printPersonById(100L);

		// Print Employement
		printEmployeeById(1L);

		System.out.println("********************");
		person.setWork(null);
		personRepository.save(person);
		printPersonById(100L);
		printEmployeeById(1L);
		System.out.println("********************");

		// Delete & Print
		log.debug("*** Deleting Person Object ***");
		personRepository.deleteById(100L);
		printPersonById(100L);

	}

	@SuppressWarnings("unchecked")
	private Person createPersonObject() {
		Person person = new Person();
		person.setFirstName("Ranga");
		person.setLastName("G");
		person.setEmail("ranga.g@test.com");
		person.setGender(Gender.MALE);
		person.setPanNumber("AAAAA1234A");
		person.setDob(Date.valueOf("1984-03-22"));
		person.setCreditCardNumber("1111-1111-1111-1111");
		return person;
	}

	private Person createDummyPersonObject() {
		Person person = new Person();
		person.setFirstName("Dummy");
		person.setLastName("Dummy");
		person.setEmail("dummy@test.com");
		person.setGender(Gender.MALE);
		person.setPanNumber("AAAAA1234A");
		person.setDob(Date.valueOf("1984-03-22"));
		person.setCreditCardNumber("1111-1111-1111-1111");
		return person;
	}

	private void printPersonById(Long id) {
		log.debug("*** Fetching from Person Repository By ID ***");
		Optional<Person> personByID = personRepository.findById(id);
		if (personByID.isPresent()) {
			log.debug("Get Person Data by ID :: " + personByID.get());
			log.debug("Get Employee Person by ID :: " + personByID.get().getWork());

		} else {
			log.error("NO Person Data for ID :: " + id);
		}
	}

	private void printEmployeeById(Long id) {
		log.debug("*** Fetching from Employement Repository By ID ***");
		Optional<Employement> employmentByID = employementRepository.findById(id);
		if (employmentByID.isPresent()) {
			log.debug("Get Employee by ID :: " + employmentByID.get());
			log.debug("Get Employee Person by ID :: " + employmentByID.get().getPerson());
		} else {
			log.error("NO Employee Data for ID :: " + id);
		}
	}

}
