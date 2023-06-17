package com.learning.ranga.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "PERSON_TB")
@DynamicUpdate
@DynamicInsert
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_ID")
	@SequenceGenerator(sequenceName = "PERSON_ID_SEQ", initialValue = 100, allocationSize = 1, name = "PERSON_ID")
	Long id;

	@Column(nullable = false)
	String firstName;

	@Column(nullable = false)
	String lastName;

	@Column(unique = true)
	String email;

//	@EmbeddedId
//	PersonId id;

	int age;

	@Enumerated(EnumType.STRING)
	Gender gender;

	@Column(name = "PAN", length = 10)
	String panNumber;

	Date dob;

	@Transient
	String creditCardNumber;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "home", column = @Column(name = "HOME_NUMBER", length = 10)) })
	Contact self;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "NICKNAMES", joinColumns = @JoinColumn(name = "PID"))
	@Column(name = "NICKNAMES", unique = true)
	List<String> nickNames = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	@ElementCollection(targetClass = Vehicle.class, fetch = FetchType.EAGER)
	List vehicles = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PHONE_NUMBERS")
	@MapKeyColumn(name = "TYPE")
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "NUMBER")
	Map<PhoneType, String> phoneNumbers = new HashMap<>();

	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "EMPLOYMENT_ID", referencedColumnName = "id")
	Employement work;

//	public PersonId getId() {
//		return id;
//	}
//
//	public void setId(PersonId id) {
//		this.id = id;
//	}

	public int getAge() {
		return age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Contact getSelf() {
		return self;
	}

	public void setSelf(Contact self) {
		this.self = self;
	}

	public List<String> getNickNames() {
		return nickNames;
	}

	public void setNickNames(List<String> nickNames) {
		this.nickNames = nickNames;
	}

	@SuppressWarnings("rawtypes")
	public List getVehicles() {
		return vehicles;
	}

	@SuppressWarnings("rawtypes")
	public void setVehicles(List vehicles) {
		this.vehicles = vehicles;
	}

	public Map<PhoneType, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Employement getWork() {
		return work;
	}

	public void setWork(Employement work) {
		this.work = work;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", age=" + age + ", gender=" + gender + ", panNumber=" + panNumber + ", dob=" + dob
				+ ", creditCardNumber=" + creditCardNumber + ", self=" + self + ", nickNames=" + nickNames
				+ ", vehicles=" + vehicles + ", phoneNumbers=" + phoneNumbers + "]";
	}

}
