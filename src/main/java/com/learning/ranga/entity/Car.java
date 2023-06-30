package com.learning.ranga.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Car {

	@Id
	@GeneratedValue(generator = "CAR_ID_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "CAR_ID_SEQ", sequenceName = "CAR_ID_SEQ", initialValue = 1, allocationSize = 1)
	Long id;

	String model;

	int noOfSeats = 4;

	String color;

	String licenseNumber;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Person owner;

	public Car() {
		super();
	}

	public Car(String model, int noOfSeats, String color, String licenseNumber, Person owner) {
		super();
		this.model = model;
		this.noOfSeats = noOfSeats;
		this.color = color;
		this.licenseNumber = licenseNumber;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", noOfSeats=" + noOfSeats + ", color=" + color
				+ ", licenseNumber=" + licenseNumber + "]";
	}

}
