package com.example.transactions.Domain.Transactions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.transactions.Advice.RecordNotFoundException;


@Service
public class TransactionsService {

	@Autowired
	private TransactionsRepository repository;

	
	@Transactional(readOnly = true)
	public Transactions getById(Long id) {
		Optional<Transactions> transactionResponse = repository.findById(id);
		if(!transactionResponse.isPresent()) {
			throw new RecordNotFoundException("601", "No record found for given id" + id);
		}
		return transactionResponse.get();
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
