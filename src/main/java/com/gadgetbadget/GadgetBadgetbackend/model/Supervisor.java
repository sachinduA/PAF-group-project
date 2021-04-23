package com.gadgetbadget.GadgetBadgetbackend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "supervisors")
public class Supervisor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supervisor_id;
	private String firstName;
	private String lastName;
	private String nic;
	private String category;

	@JsonIgnore
	@OneToMany(mappedBy = "supervisor")
	private Set<Research> researches;

	public Supervisor() {
	}

	public Supervisor(int supervisor_id, String firstName, String lastName, String nic, String category) {
		super();
		this.supervisor_id = supervisor_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nic = nic;
		this.category = category;
	}

	public int getSupervisor_id() {
		return supervisor_id;
	}

	public void setSupervisor_id(int supervisor_id) {
		this.supervisor_id = supervisor_id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Research> getResearches() {
		return researches;
	}

	public void setResearches(Set<Research> researches) {
		this.researches = researches;
	}

}
