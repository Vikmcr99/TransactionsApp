package com.example.transactions.Domain.Transactions;

import java.time.LocalDateTime;

import com.example.transactions.Domain.Account.Account;
import com.example.transactions.Domain.Merchant.Merchant;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transactions")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private Double transaction_value;
	private LocalDateTime transaction_time;
	private Boolean transaction_status;

	@ManyToOne(cascade = CascadeType.DETACH)
	private Account account;
	@OneToOne(cascade = CascadeType.DETACH)
	private Merchant merchant;
	
	public Transactions(Long id, Double transaction_value, LocalDateTime transaction_time, 
			Boolean transaction_status, Account account, Merchant merchant) {
		this.id = id;
		this.transaction_value = transaction_value;
		this.transaction_time = transaction_time;
		this.transaction_status = transaction_status;
		this.account = account;
		this.merchant = merchant;	}
	
	public Transactions() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(LocalDateTime transaction_time) {
		this.transaction_time = transaction_time;
	}

	public Boolean getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(Boolean transaction_status) {
		this.transaction_status = transaction_status;
	}

	public Double getTransaction_value() {
		return transaction_value;
	}

	public void setTransaction_value(Double transaction_value) {
		this.transaction_value = transaction_value;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	
	
	
}
