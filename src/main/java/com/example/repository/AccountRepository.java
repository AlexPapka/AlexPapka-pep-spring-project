package com.example.repository;


import com.example.entity.Account;

import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("AccountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer>{

      
       
    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Account findByUsername(@Param("username") String username);

    @Query("SELECT a FROM Account a WHERE a.accountId = :accountId")
    Account findAccountByMessageId(@Param("accountId") int accountId);
} 
 