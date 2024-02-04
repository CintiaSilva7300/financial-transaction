package com.financialtransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financialtransaction.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
