package com.learning.ranga.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Vehicle {
	String license;
	String type;

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Vehicle [license=" + license + ", type=" + type + "]";
	}

}
