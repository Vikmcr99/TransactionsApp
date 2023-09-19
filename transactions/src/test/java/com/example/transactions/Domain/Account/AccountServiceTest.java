package com.example.transactions.Domain.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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
public class AccountServiceTest {
	
	@InjectMocks
	AccountService service;
	
	@Mock
	AccountRepository repository;
	
	Account account;
	
	@BeforeEach
	
	public void setup() {
		account = new Account(100L, "Josefa", 500.0, true, null);
	}
	
	@Test
	void getAllAccountsTest() {
		when(repository.findAll()).thenReturn(Collections.singletonList(account));
		
		List<Account> accounts = service.getAllAccounts();
		
		assertEquals(Collections.singletonList(account), accounts);
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void createAccountTest() {
		when(repository.save(account)).thenReturn(account);
		Account acc = service.createAccount(account);
		assertEquals(account, acc);
		verify(repository).save(account);
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void deleteAccountTest() {
		long accountIdToDelete = 100L;
		doNothing().when(repository).deleteById(accountIdToDelete);;
		service.deleteAccount(accountIdToDelete);
		verify(repository, times(1)).deleteById(accountIdToDelete);
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void getAccountByIdTest() {
		
		Optional<Account> optionalAccount = Optional.of(account);
		when(repository.findById(account.getId())).thenReturn(optionalAccount);
		
		Account retrievedAccount = service.getById(account.getId());

		assertTrue(optionalAccount.isPresent());
		assertEquals(account, retrievedAccount);
		verify(repository).findById(account.getId());
		verifyNoMoreInteractions(repository);
	}

}
