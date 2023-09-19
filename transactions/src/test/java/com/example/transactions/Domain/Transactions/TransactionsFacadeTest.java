package com.example.transactions.Domain.Transactions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.transactions.Domain.Account.Account;
import com.example.transactions.Domain.Merchant.Merchant;

public class TransactionsFacadeTest {

    @InjectMocks
    private TransactionsFacade facade;

    @Mock
    private TransactionsRepository repository;

    @Mock
    private TransactionsService service;
    
    Transactions transactions;
    
    Account account;
    
    Merchant merchant;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); 
        transactions = new Transactions(100L, 100.0, LocalDateTime.now(), true, null, null );
        account = new Account(1L, "Josefa", 5000.0, true, null);
        merchant = new Merchant(1L, "121512121512", null);

    }

    @Test
    void testAuthorizeTransaction() {
        when(repository.findMaxId()).thenReturn(99L);
        when(repository.findAll()).thenReturn(Collections.emptyList()); //Simula que não há transações existentes
        when(service.createNewTransaction(any(Transactions.class))).thenReturn(transactions); //Simula que a criação da transação foi ok

        boolean isAuthorized = facade.authorizeTransaction(account, 500.0, merchant);

        assertTrue(isAuthorized);
    }
    
    @Test
    void testIsDuplicateTransaction() {
        
        Transactions existingTransaction = new Transactions(1L, 100.0, LocalDateTime.now(), true, account, merchant);

        when(service.getAllTransactions()).thenReturn(Collections.singletonList(existingTransaction));


        boolean isDuplicate = facade.isDuplicateTransaction(account, 100.0, merchant);


        assertTrue(isDuplicate);
    }
    
    @Test
    void testHasExceededTransactionLimit() {

        List<Transactions> transactionsList = new ArrayList<>();
        LocalDateTime currentTime = LocalDateTime.now();

        for (int i = 0; i < 3; i++) {
            Transactions transaction = new Transactions(i + 1L, 100.0, currentTime.plusMinutes(i), true, account, merchant);
            transactionsList.add(transaction);
        }

        when(service.getAllTransactions()).thenReturn(transactionsList);

        boolean hasExceededLimit = facade.hasExceededTransactionLimit(account);

        assertTrue(hasExceededLimit);
    }
}