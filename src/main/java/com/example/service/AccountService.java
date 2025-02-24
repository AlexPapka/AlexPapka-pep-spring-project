package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

public class AccountService {

    //public AccountRepository accountRepository;

    //public AccountService(){
        //accountRepository = new AccountRepository();
    //}

    public Account registerAccount(Account account){
        if(account.getUsername() == ""){
            return null;
        }
        if(account.getPassword().length() <= 4){
            return null;
        }
        if(AccountRepository.getAccount(account) == account.getUsername()){   // will be null if no account
            return account; // returns an account with no id
        }
        
        Account returnedAccount = new Account();
        returnedAccount = AccountRepository.registerAccount(account);
        return returnedAccount;
    }



}  
