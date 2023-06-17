package com.learning.ranga;

import java.sql.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.ranga.entity.Contact;
import com.learning.ranga.entity.Employement;
import com.learning.ranga.entity.Gender;
import com.learning.ranga.entity.Person;
import com.learning.ranga.entity.PhoneType;
import com.learning.ranga.entity.Vehicle;
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

		// Save Employment	
		Employement emp = new Employement("MyWork", "MW001", "BLR", "KA");
		emp = employementRepository.save(emp);

		Person dummyP = createDummyPersonObject();
		Employement dummyW = new Employement("MyWork", "MW000", "BLR", "KA");
		dummyW = employementRepository.save(dummyW);
		dummyP.setWork(dummyW);
		personRepository.save(dummyP);
		dummyP.setWork(null);
		personRepository.save(dummyP);
		
		// Update Person with Work
		person.setWork(emp);

		// Save Person
		Person savedPerson = personRepository.save(person);

		// Print Person
		printPersonById(100L);

		// Update
		emp.setState("AP");
		savedPerson.setAge(40);
		personRepository.save(savedPerson);

		// Print Emp
		printEmployeeById(1L);

		// Delete & Print
		log.debug("*** Deleting Person Object ***");
		personRepository.deleteById(100L);
		printPersonById(100L);
		printEmployeeById(1L);
		printEmployeeById(2L);

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

		person.getNickNames().add("Ranga");
		person.getNickNames().add("RangaG");

		Vehicle twoW = new Vehicle();
		twoW.setLicense("IN001");
		twoW.setType("2W");

		Vehicle fourW = new Vehicle();
		fourW.setLicense("IN002");
		fourW.setType("4W");

		person.getVehicles().add(twoW);
		person.getVehicles().add(fourW);

		person.getPhoneNumbers().put(PhoneType.MOBILE, "99999 99999");
		person.getPhoneNumbers().put(PhoneType.HOME, "080 1199999");
		person.getPhoneNumbers().put(PhoneType.WORK, "080 888888");

		Contact contact = new Contact();
		contact.setMobile("9999911111");
		contact.setHome("1234567890");
		person.setSelf(contact);
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

		Contact contact = new Contact();
		contact.setMobile("9999911111");
		contact.setHome("1234567890");
		person.setSelf(contact);
		return person;
	}

	private void printPersonById(Long id) {
		log.debug("*** Fetching from Person Repository By ID ***");
		Optional<Person> personByID = personRepository.findById(id);
		if (personByID.isPresent()) {
			log.debug("Get Person Data by ID :: " + personByID.get());
			log.debug("Get Person Employee Data by ID :: " + personByID.get().getWork());
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
