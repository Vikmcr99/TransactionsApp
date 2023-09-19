package com.example.transactions.Domain.Merchant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class MerchantServiceTest {
	
	@InjectMocks
	MerchantService service;
	
	@Mock
	MerchantRepository repository;
	
	Merchant merchant;
	
	@BeforeEach
	
	public void setup() {
		merchant = new Merchant(100L, "121512121512", null);
	}
	
	@Test
	void getAllMerchantsTest() {
		when(repository.findAll()).thenReturn(Collections.singletonList(merchant));
		
		List<Merchant> merchants = service.getAllMerchants();
		
		assertEquals(Collections.singletonList(merchant), merchants);
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void createMerchantTest() {
		when(repository.save(merchant)).thenReturn(merchant);
		Merchant acc = service.createNewMerchant(merchant);
		assertEquals(merchant, acc);
		verify(repository).save(merchant);
		verifyNoMoreInteractions(repository);
	}
	
	
	@Test
	void getMerchantByIdTest() {
		
		Optional<Merchant> optionalMerchant = Optional.of(merchant);
		when(repository.findById(merchant.getId())).thenReturn(optionalMerchant);
		
		Merchant retrievedMerchant = service.getById(merchant.getId());

		assertTrue(optionalMerchant.isPresent());
		assertEquals(merchant, retrievedMerchant);
		verify(repository).findById(merchant.getId());
		verifyNoMoreInteractions(repository);
	}

}
