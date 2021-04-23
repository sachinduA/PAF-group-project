package com.gadgetbadget.GadgetBadgetbackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "funders")
public class Funder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int funder_id;
	private String firstName;
	private String lastName;
	private String nic;
	private String contactNo;

	@JsonIgnore
	@ManyToMany(mappedBy = "researchers")
	private Set<Research> researches = new HashSet<>();

	public Funder() {
	}

	public Funder(int funder_id, String firstName, String lastName, String nic) {
		super();
		this.funder_id = funder_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nic = nic;
	}

	public int getFunder_id() {
		return funder_id;
	}

	public void setFunder_id(int funder_id) {
		this.funder_id = funder_id;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Set<Research> getResearches() {
		return researches;
	}

	public void setResearches(Set<Research> researches) {
		this.researches = researches;
	}

}
