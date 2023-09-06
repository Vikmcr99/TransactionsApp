package com.example.transactions.Domain.Transactions;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/transactions", produces = {"application/json"})
@Tag(name = "TransactionsApp - TransactionsController")
public class TransactionsController {
	
	@Autowired
	private TransactionsService service;
	
	@Autowired
	private TransactionsFacade facade;
	
	@GetMapping
	public ResponseEntity<List<Transactions>> getAllTransactions(){
		List<Transactions> transactions = service.getAllTransactions();

        if (transactions == null || transactions.isEmpty()) {
            return new ResponseEntity<List<Transactions>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);

	}
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Transactions> findById (@PathVariable ("id") Long id){
		try {
            Transactions transaction = service.getById(id);

            return new ResponseEntity<Transactions>(transaction, HttpStatus.OK);
        } 
		
		catch (NoSuchElementException ns) {
            return new ResponseEntity<Transactions>(HttpStatus.NOT_FOUND);
        }
	
	}
	
	@PostMapping
	public ResponseEntity<Transactions> createNewTransaction (@RequestBody Transactions transaction) {
		service.createNewTransaction(transaction);
		return new ResponseEntity<Transactions>(transaction, HttpStatus.OK);
	}
	
	@PostMapping(value = "/authorize")
    public ResponseEntity<Transactions> authorizeTransaction(@RequestBody Transactions transaction) {

        boolean isAuthorized = facade.authorizeTransaction(transaction.getAccount(), 
        		transaction.getTransaction_value(), transaction.getMerchant());

        if (isAuthorized) {
            return new ResponseEntity<Transactions>(transaction, HttpStatus.OK);
        } 
        
        else {
        	return new ResponseEntity<Transactions>(HttpStatus.UNAUTHORIZED);
        }
    }
	
}
