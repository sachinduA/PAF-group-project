package com.gadgetbadget.GadgetBadgetbackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "researches")
public class Research {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int research_id;
	private String name;
	private String description;
	private String category;

	@ManyToMany()
	@JoinTable(name = "research_management", joinColumns = @JoinColumn(name = "research_id"), inverseJoinColumns = @JoinColumn(name = "researcher_id"))
	private Set<Researcher> researchers = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "buyer_id")
	private Buyer buyer;

	@ManyToMany()
	@JoinTable(name = "research_fundings", joinColumns = @JoinColumn(name = "research_id"), inverseJoinColumns = @JoinColumn(name = "funder_id"))
	private Set<Funder> funders = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supervisor_id")
	private Supervisor supervisor;

	public Research() {

	}

	public Research(int research_id, String name, String description, String category) {
		super();
		this.research_id = research_id;
		this.name = name;
		this.description = description;
		this.category = category;
	}

	public int getResearch_id() {
		return research_id;
	}

	public void setResearch_id(int research_id) {
		this.research_id = research_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Researcher> getResearchers() {
		return researchers;
	}

	public void setResearchers(Set<Researcher> researchers) {
		this.researchers = researchers;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Set<Funder> getFunders() {
		return funders;
	}

	public void setFunders(Set<Funder> funders) {
		this.funders = funders;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

}
