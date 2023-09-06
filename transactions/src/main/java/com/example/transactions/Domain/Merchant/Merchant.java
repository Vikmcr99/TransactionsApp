package com.example.transactions.Domain.Merchant;

import com.example.transactions.Domain.Transactions.Transactions;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_merchants")
public class Merchant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String cnpj;
	@OneToOne(mappedBy = "merchant")
	@JsonBackReference
	private Transactions transactions;
	
	public Merchant(Long id, String cnpj, Transactions transactions) {
		this.id = id;
		this.cnpj = cnpj;
		this.transactions = transactions;
	}
	
	public Merchant () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Transactions getTransactions() {
		return transactions;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}
	
	
	
}
