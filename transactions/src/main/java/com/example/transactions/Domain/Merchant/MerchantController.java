package com.example.transactions.Domain.Merchant;

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
@RequestMapping(value = "/merchants", produces = {"application/json"})
@Tag(name = "TransactionsApp - MerchantsController")
public class MerchantController {

	@Autowired
	private MerchantService service;
	
	@GetMapping
	public ResponseEntity<List<Merchant>> getAllMerchants(){
		List<Merchant> merchants = service.getAllMerchants();
		
		if ( merchants == null || merchants.isEmpty()) {
			return new ResponseEntity<List<Merchant>>(merchants, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Merchant>>(merchants, HttpStatus.OK);
	}
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Merchant> findById (@PathVariable ("id")Long id){
		try {
			Merchant merchant = service.getById(id);
			
			return new ResponseEntity<Merchant>(merchant, HttpStatus.OK);
		}
		
		catch (NoSuchElementException ns) {
			return new ResponseEntity<Merchant>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Merchant> createNewMerchant (@RequestBody Merchant merchant) {
		service.createNewMerchant(merchant);
		return new ResponseEntity<Merchant>(merchant, HttpStatus.OK);
	}
	
}
