package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){
        if(account.getUsername() == ""){
            return null;
        }
        if(account.getPassword().length() <= 4){
            return null;
        }
        if(accountRepository.findByUsername(account.getUsername()).getUsername() == account.getUsername()){   // will be null if no account
            return account; // returns an account with no id
        }
        
        Account returnedAccount = new Account();
        returnedAccount = accountRepository.registerAccount(account.getUsername(), account.getPassword());
        return returnedAccount;
    }



}  
