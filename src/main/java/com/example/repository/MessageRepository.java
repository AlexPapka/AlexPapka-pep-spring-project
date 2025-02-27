package com.example.repository;

import java.sql.*;
import java.util.List;

import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository("MessageRepository")
public interface MessageRepository extends JpaRepository<Message, Integer> {

    //@Query("SELECT m FROM Message m WHERE m.account.id = :accountId ")
    //List<Message> findByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT m FROM  Message m WHERE m.postedBy = :postedBy")
    Account findAccountByMessageId(@Param("postedBy") Integer postedBy);

    @Query("SELECT m FROM Message m")
    List<Message> getAllMessages();

    @Query("SELECT m FROM Message m WHERE m.messageId = :messageId")
    Message getMessageById(@Param("messageId") String messageId);
} 
