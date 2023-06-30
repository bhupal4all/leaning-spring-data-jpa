package com.learning.ranga;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.ranga.entity.Car;
import com.learning.ranga.entity.Gender;
import com.learning.ranga.entity.Person;
import com.learning.ranga.repository.CarRepository;
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
	CarRepository carRepository;

	@Override
	public void run(String... args) throws Exception {

		log.debug("** Spring Data JPA **");

		Person person = createPersonObject();
		Set<Car> cars = new HashSet<>();
		cars.add(new Car("Maruti", 4, "Gray", "IN001", person));
		cars.add(new Car("Suzuki", 4, "Red", "IN002", person));
		cars.add(new Car("Honda", 4, "Blue", "IN003", person));
				
		person.setCars(cars);

		// Save Person
		personRepository.save(person);

		// Print Person
		printPersonById(100L);

		// Print Cars
		printCarsById(1L, 2L, 3L);

		// Delete & Print
		log.debug("*** Deleting Person Object ***");
		personRepository.deleteById(100L);
		printPersonById(100L);
		printCarsById(1L, 2L, 3L);

	}

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

	private void printPersonById(Long id) {
		log.debug("*** Fetching from Person Repository By ID ***");
		Optional<Person> personByID = personRepository.findById(id);
		if (personByID.isPresent()) {
			log.debug("Get Person Data by ID :: " + personByID.get());
			log.debug("Get Cars Person by ID :: " + personByID.get().getCars());

		} else {
			log.error("NO Person Data for ID :: " + id);
		}
	}

	private void printCarsById(Long... ids) {
		log.debug("*** Fetching from Car Repository By <IDs> ***");
		List<Long> idsList = Arrays.asList(ids);
		idsList.forEach(this::printCarById);
	}

	private void printCarById(Long id) {
		Optional<Car> carByID = carRepository.findById(id);
		if (carByID.isPresent()) {
			log.debug("Get Car by ID :: " + carByID.get());
			log.debug("Get Car Person by ID :: " + carByID.get().getOwner());
		} else {
			log.error("NO Car Data for ID :: " + id);
		}
	}

}
