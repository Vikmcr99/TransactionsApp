package com.example.transactions.Domain.Merchant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.transactions.RecordNotFoundException;



@Service
public class MerchantService {
	@Autowired
	private MerchantRepository repository;
	
	@Transactional(readOnly = true)
	public Merchant getById(Long id) {
		
		Optional<Merchant> merchantResponse = repository.findById(id);
		
		if (!merchantResponse.isPresent()) {
				throw new RecordNotFoundException("No record found for given id" + id);
			}
		
		return merchantResponse.get();
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
