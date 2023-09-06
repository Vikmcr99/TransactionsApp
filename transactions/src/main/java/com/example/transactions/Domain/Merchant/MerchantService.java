package com.example.transactions.Domain.Merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MerchantService {
	@Autowired
	private MerchantRepository repository;
	
	@Transactional(readOnly = true)
	public Merchant getById(Long id) {
		
		return repository.findById(id).get();
	}
	
	public List<Merchant> getAllMerchants(){
		List<Merchant> result = repository.findAll();
		return result;
	}
	
	public Merchant createNewMerchant(Merchant merchant){
		Merchant result = repository.save(merchant);
		return result;
	}
	

}
