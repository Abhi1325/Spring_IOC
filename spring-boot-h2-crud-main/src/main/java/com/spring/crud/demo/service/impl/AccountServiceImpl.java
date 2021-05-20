package com.spring.crud.demo.service.impl;

import com.spring.crud.demo.model.AccountInfo;
import com.spring.crud.demo.model.Transactions;
import com.spring.crud.demo.repository.AccountRepository;
import com.spring.crud.demo.repository.TransactionRepository;
import com.spring.crud.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<AccountInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public AccountInfo findByAccountnumber(int accountNumber) {
        return repository.findById(accountNumber).orElseThrow(() -> new NotFoundException("** Employee not found for id :: " + accountNumber));
    }

    @Override
    public AccountInfo save(AccountInfo accountInfo) {
		/*
		 * if(Optional.ofNullable(accountInfo.getBalance()) != null) {
		 * accountInfo.setBalance(accountInfo.getAmount()); }
		 */
        return repository.save(accountInfo);
    }

	@Override
	public Transactions saveTransaction(Transactions transactions) {
		// TODO Auto-generated method stub
		return transactionRepository.save(transactions);
	}

	

    
}
