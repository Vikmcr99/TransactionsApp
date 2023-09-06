package com.example.transactions.Domain.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TransactionsRepository extends JpaRepository <Transactions, Long>{
	@Query(value = "SELECT MAX(ID) FROM TB_TRANSACTIONS", nativeQuery = true)
    Long findMaxId();
}
