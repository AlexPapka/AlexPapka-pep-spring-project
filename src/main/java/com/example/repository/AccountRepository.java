package com.example.repository;

import com.example.entity.Account;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;

public interface AccountRepository {

    static Account registerAccount(Account account){
        
        
        return account;
    }
    @Autowired
    static String getAccount(Account account){
        String sql = "SELECT * FROM account WHERE username = ?";
         
        
        
        
        return null;
    }
} 
 