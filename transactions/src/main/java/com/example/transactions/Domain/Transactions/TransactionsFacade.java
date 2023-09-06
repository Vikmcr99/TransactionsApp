package com.example.transactions.Domain.Transactions;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.transactions.Domain.Account.Account;
import com.example.transactions.Domain.Merchant.Merchant;

@Component
public class TransactionsFacade {
	@Autowired
	private TransactionsRepository repository;
	
	@Autowired
	private TransactionsService service;

	public boolean authorizeTransaction(Account account, Double amount, Merchant merchant) {
        if (!account.getActive_card()) {
            return false;
        }

        if (amount > account.getAvailable_limit()) {
            return false;
        }
        
        if (isDuplicateTransaction(account, amount, merchant)) {
            return false;
        }

        if (hasExceededTransactionLimit(account)) {
            return false;
        }

        Long newId = repository.findMaxId() + 1;
        
        Transactions transaction = new Transactions(newId, amount, LocalDateTime.now(),true, account, merchant);
        service.createNewTransaction(transaction);
        return true;
    }
	
	private boolean isDuplicateTransaction(Account account, double amount, Merchant merchant) {
        for (Transactions transaction : service.getAllTransactions()) {
            if (transaction.getAccount().equals(account) && transaction.getMerchant().equals(merchant) 
            		&& transaction.getTransaction_value() == amount) {
                return true; 
            }
        }
        return false;
    } 

    private boolean hasExceededTransactionLimit(Account account) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime myTransactionTimeInterval = currentTime.minusMinutes(2);
   
        int count = 0;
        
        for (Transactions transaction : service.getAllTransactions()) {
            if (transaction.getAccount().equals(account) && transaction.getTransaction_time().isAfter(myTransactionTimeInterval) ) {
                count++;
            }
        }

        return count >= 3;
    }
}
