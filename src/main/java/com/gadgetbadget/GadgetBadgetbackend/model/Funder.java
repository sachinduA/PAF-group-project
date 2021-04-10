package com.gadgetbadget.GadgetBadgetbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "funders")
public class Funder {
	private int id;
	private String firstName;
	private String lastName;
	private String nic;

	public Funder() {
	}

	public Funder(int id, String firstName, String lastName, String nic) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nic = nic;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}
}
