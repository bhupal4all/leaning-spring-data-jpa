package com.learning.ranga.entity;

import java.sql.Date;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
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

	int age;

	@Enumerated(EnumType.STRING)
	Gender gender;

	@Column(name = "PAN", length = 10)
	String panNumber;

	Date dob;

	@Transient
	String creditCardNumber;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(
			name = "P_C_MAPPING",
			joinColumns = @JoinColumn(name="PID", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="CID", referencedColumnName = "id")
			)
	Set<Car> cars;

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

	public int getAge() {
		return age;
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

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", age=" + age + ", gender=" + gender + ", panNumber=" + panNumber + ", dob=" + dob
				+ ", creditCardNumber=" + creditCardNumber + "]";
	}

}
