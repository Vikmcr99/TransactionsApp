package com.example.transactions.Domain.Transactions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class TransactionsControllerTest {
	
@InjectMocks
TransactionsController controller;

@Mock
TransactionsService service;

@Mock
TransactionsFacade facade;

MockMvc mockmvc;

private Transactions transactions;

@BeforeEach
public void setup() {
	mockmvc = MockMvcBuilders.standaloneSetup(controller)
			.alwaysDo(print()).build();
	
	transactions = new Transactions(200L, 100.0, LocalDateTime.now(), true, 
			null, null );
}

@Test
void MustAuthorizeATransaction() {
	when(facade.authorizeTransaction(transactions.getAccount(), transactions.getTransaction_value(), transactions.getMerchant()))
	.thenReturn(true);
	
	boolean isAuthorized = facade.authorizeTransaction(
	        transactions.getAccount(),
	        transactions.getTransaction_value(),
	        transactions.getMerchant()
	    );
	
	 assertTrue(isAuthorized);
}

@Test

void TransactionNotAuthorized() {
   
	when(facade.authorizeTransaction(transactions.getAccount(), transactions.getTransaction_value(), transactions.getMerchant()))
	.thenReturn(false);

    boolean isAuthorized = facade.authorizeTransaction(
	        transactions.getAccount(),
	        transactions.getTransaction_value(),
	        transactions.getMerchant()
	    );

    assertFalse(isAuthorized);
}

}
