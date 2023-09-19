package com.example.transactions.Domain.Transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
public class TransactionsServiceTest {
	
	@InjectMocks
	TransactionsService service;
	
	@Mock
	TransactionsRepository repository;
	
	Transactions transaction;
	
	@BeforeEach
	
	public void setup() {
		transaction = new Transactions(100L, 100.0, LocalDateTime.now(), true, null, null );
	}
	
	@Test
	void getAllTransactionssTest() {
		when(repository.findAll()).thenReturn(Collections.singletonList(transaction));
		
		List<Transactions> transactions = service.getAllTransactions();
		
		assertEquals(Collections.singletonList(transaction), transactions);
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void createTransactionsTest() {
		when(repository.save(transaction)).thenReturn(transaction);
		Transactions acc = service.createNewTransaction(transaction);
		assertEquals(transaction, acc);
		verify(repository).save(transaction);
		verifyNoMoreInteractions(repository);
	}
	
	
	@Test
	void getTransactionsByIdTest() {
		
		Optional<Transactions> optionalTransactions = Optional.of(transaction);
		when(repository.findById(transaction.getId())).thenReturn(optionalTransactions);
		
		Transactions retrievedTransactions = service.getById(transaction.getId());

		assertTrue(optionalTransactions.isPresent());
		assertEquals(transaction, retrievedTransactions);
		verify(repository).findById(transaction.getId());
		verifyNoMoreInteractions(repository);
	}

}
