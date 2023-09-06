package com.example.transactions.Domain.Account;

import java.util.List;
import java.util.NoSuchElementException;

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
	
	/*@GetMapping
	public List<Account> getAccounts() {
		Account ac1 = new Account(1L, "Joao", (double)2000, true);
		Account ac2 = new Account(2L, "Victor", (double)25000, true);
		Account ac3 = new Account(3L, "Rafael", (double)800, true);
		Account ac4 = new Account(4L, "Souza", (double)10000, true);
		Account ac5 = new Account(5L, "Pedro", (double)100, false);
		
		List<Account> list = Arrays.asList(ac1,ac2,ac3,ac4,ac5);
		return list;
	}*/
	
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
		try {
            Account account = service.getById(id);

            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } 
		
		catch (NoSuchElementException ns) {
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
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
