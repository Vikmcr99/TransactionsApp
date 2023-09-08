package com.example.transactions.Domain.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;

	@Transactional(readOnly = true)
	public Account getById(Long id) {
		
		Optional<Account> accountResponse = repository.findById(id);
		
		if (!accountResponse.isPresent()) {
				throw new RuntimeException("No record found for given id" + id);
			}
		
		return accountResponse.get();
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
