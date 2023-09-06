package com.example.transactions.Domain.Transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionsService {

	@Autowired
	private TransactionsRepository repository;

	
	@Transactional(readOnly = true)
	public Transactions getById(Long id) {
		return repository.findById(id).get();
	}
	
	public List<Transactions> getAllTransactions(){
		List<Transactions> result = repository.findAll();
		return result;
	}
	
	public Transactions createNewTransaction(Transactions transactions){
		Transactions result = repository.save(transactions);
		return result;
	}
	
}
