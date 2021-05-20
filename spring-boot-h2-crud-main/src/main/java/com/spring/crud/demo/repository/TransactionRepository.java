package com.spring.crud.demo.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.crud.demo.model.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
	List<Transactions> findByAccountNumberAndType(int accountNumber, String type);
	List<Transactions> findByLastUpdateTimestampBetween(Timestamp timestamp, Timestamp timestamp2);

}
