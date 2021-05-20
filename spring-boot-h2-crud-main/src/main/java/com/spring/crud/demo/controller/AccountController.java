package com.spring.crud.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.crud.demo.model.AccountInfo;
import com.spring.crud.demo.model.Transactions;
import com.spring.crud.demo.repository.AccountRepository;
import com.spring.crud.demo.repository.TransactionRepository;
import com.spring.crud.demo.service.AccountService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/accountinfo")
public class AccountController {

	@Autowired
	private AccountService service;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	float amount = 0;
	/*
	 * @GetMapping public ResponseEntity<List<?>> findAll() { List<?> list =
	 * service.findAll(); return ResponseEntity.ok().body(list); }
	 */

	@GetMapping
	public ResponseEntity<?> findByAccountNumber(@RequestParam int accountNumber) {
		List<AccountInfo> employee = accountRepository.findByAccountNumber(accountNumber);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/type")
	public ResponseEntity<?> withdrawOrDeposite(@RequestBody Transactions transactions) {
		AccountInfo accountInfo = new AccountInfo();
		Transactions transaction = service.saveTransaction(transactions);
		List<AccountInfo> amountList = accountRepository.findByAccountNumber(transactions.getAccountNumber());
		for (AccountInfo balance : amountList) {
			amount = balance.getBalance();
		}
		if (StringUtils.isEmpty(transaction.getType()) && transactions.getType().equalsIgnoreCase("WITHDRAW")) {
			accountInfo.setBalance(amount - transactions.getAmount());
		} else if (StringUtils.isEmpty(transaction.getType()) && transactions.getType().equalsIgnoreCase("DEPOSITE")) {
			accountInfo.setBalance(amount + transactions.getAmount());
		}
		accountRepository.update(accountInfo.getBalance(), transactions.getAccountNumber());

		return ResponseEntity.ok().body(transaction);
	}

	@GetMapping("/latestbal")
	public ResponseEntity<?> deposite(@RequestParam int accountNumber) {
		List<AccountInfo> LatestBalance = accountRepository.findByAccountNumber(accountNumber);
		return ResponseEntity.ok().body(LatestBalance);
	}

	@GetMapping("/daterange")
	public ResponseEntity<?> timeRange(@RequestParam Timestamp timestamp, @RequestParam Timestamp timestamp2) {
		List<Transactions> LatestBalance = transactionRepository.findByLastUpdateTimestampBetween(timestamp,
				timestamp2);
		return ResponseEntity.ok().body(LatestBalance);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody AccountInfo accountInfo) {
		AccountInfo acInfo = service.save(accountInfo);
		return ResponseEntity.ok().body(acInfo);
	}

}
