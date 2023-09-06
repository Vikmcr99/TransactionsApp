package com.example.transactions.Domain.Account;


import java.util.List;

import com.example.transactions.Domain.Transactions.Transactions;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String owner;
	private Double available_limit;
	private Boolean active_card;
	@OneToMany(mappedBy = "account")
	@JsonBackReference
	private List<Transactions> transactions;

	public Account(Long id, String owner, Double available_limit, Boolean active_card, List<Transactions> transactions) {
		this.id = id;
		this.owner = owner;
		this.available_limit = available_limit;
		this.active_card = active_card;
		this.transactions = transactions;

	}

	public Account () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Double getAvailable_limit() {
		return available_limit;
	}

	public void setAvailable_limit(Double available_limit) {
		this.available_limit = available_limit;
	}

	public Boolean getActive_card() {
		return active_card != null ? active_card : false;
	}

	public void setActive_card(Boolean active_card) {
		this.active_card = active_card;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	
	
}
