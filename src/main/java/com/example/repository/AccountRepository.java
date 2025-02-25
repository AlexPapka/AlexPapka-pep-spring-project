package com.example.repository;


import com.example.entity.Account;

import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

      
            
    @Query("FROM account WHERE username = ?1")
    Account findByUsername(String username);

    @Query("INSERT INTO account (username,password) VALUES (?1, ?2)")
    Account registerAccount(@Param("username") String username,@Param("password") String password);
    
} 
 