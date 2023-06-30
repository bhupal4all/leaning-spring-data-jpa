package com.learning.ranga.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

/**
 * @author ranga
 *
 */
@Entity
public class Employement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5424866709586408643L;

	@Id
	@GeneratedValue(generator = "EMPLOYEMENT_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "EMPLOYEMENT_ID", sequenceName = "EMPLOYEMENT_SEQ", allocationSize = 1, initialValue = 1)
	Long id;

	String name;

	String employeeId;

	String city;

	String state;

	@OneToOne(mappedBy = "work", optional = true)
	@Transient
	Person person;

	public Employement() {
		super();
	}

	public Employement(String name, String employeeId, String city, String state) {
		super();
		this.name = name;
		this.employeeId = employeeId;
		this.city = city;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Employement [id=" + id + ", name=" + name + ", employeeId=" + employeeId + ", city=" + city + ", state="
				+ state + "]";
	}

}
