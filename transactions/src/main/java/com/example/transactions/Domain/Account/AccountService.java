package com.example.transactions.Domain.Account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;

	@Transactional(readOnly = true)
	public Account getById(Long id) {
		
		return repository.findById(id).get();
	}
	

	public List<Account> getAllAccounts(){
		List<Account> result = repository.findAll();
		return result;
	}
	
	public Account createAccount(Account account){
		Account result = repository.save(account);
		return result;
	}
	
	public void deleteAccount(Long id) {
		 repository.deleteById(id);
	}
	

}
