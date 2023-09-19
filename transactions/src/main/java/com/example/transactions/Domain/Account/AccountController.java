package com.example.transactions.Domain.Account;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/accounts", produces = {"application/json"})
@Tag(name = "TransactionsApp - AccountsController")

public class AccountController {
	
	@Autowired
	private AccountService service;
	

	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts(){
		List<Account> accounts =  service.getAllAccounts();
		
		 if (accounts == null || accounts.isEmpty()) {
	            return new ResponseEntity<List<Account>>(HttpStatus.NO_CONTENT);
	        }
	        
	        return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Account> findById(@PathVariable ("id") Long id) {
       Account account = service.getById(id);
       return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		service.createAccount(account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity deleteAccount(@PathVariable ("id") Long id) {
		service.deleteAccount(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}
