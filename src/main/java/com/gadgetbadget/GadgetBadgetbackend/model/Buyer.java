package com.gadgetbadget.GadgetBadgetbackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "buyers")
public class Buyer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int buyer_id;
	private String firstName;
	private String lastName;
	private String nic;
	private String contactNo;

	@JsonIgnore
	@OneToMany(mappedBy = "buyer")
	private Set<Research> researches = new HashSet<>();

	public Buyer() {

	}

	public Buyer(int buyer_id, String firstName, String lastName, String nic) {
		super();
		this.buyer_id = buyer_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nic = nic;
	}

	public int getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
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
