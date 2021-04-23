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
@Table(name = "researchers")
public class Researcher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int researcher_id;
	private String firstName;
	private String lastName;
	private String nic;

	@JsonIgnore
	@ManyToMany(mappedBy = "researchers")
	private Set<Research> researches = new HashSet<>();

	public Researcher() {
	}

	public Researcher(int researcher_id, String firstName, String lastName, String nic) {
		super();
		this.researcher_id = researcher_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nic = nic;
	}

	public int getResearcher_id() {
		return researcher_id;
	}

	public void setResearcher_id(int researcher_id) {
		this.researcher_id = researcher_id;
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

	public Set<Research> getResearches() {
		return researches;
	}

	public void setResearches(Set<Research> researches) {
		this.researches = researches;
	}

}
