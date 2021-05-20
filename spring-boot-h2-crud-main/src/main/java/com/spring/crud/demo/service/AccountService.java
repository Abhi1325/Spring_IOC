package com.spring.crud.demo.service;



import java.util.List;

import com.spring.crud.demo.model.AccountInfo;
import com.spring.crud.demo.model.Transactions;

public interface AccountService {

    List<?> findAll();

    AccountInfo findByAccountnumber(int accountNumber);

    AccountInfo save(AccountInfo accountInfo);
    
    Transactions saveTransaction(Transactions accountInfo);

}
