package com.example.controller;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import org.jboss.logging.Messages;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.entity.Message;
import com.example.entity.Account;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */ 
@RestController
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService; 
        this.messageService = messageService;
    }
 
     
    @PostMapping(value = "/register")   // 1
    public ResponseEntity registerAccount(@RequestBody Account register){
        Account returnedAccount = new Account();
        returnedAccount = accountService.registerAccount(register);
        if(returnedAccount == null){
            return ResponseEntity.status(400).body("Client error");
        }else if(returnedAccount.getAccountId() == null){
            return ResponseEntity.status(409).body("Conflict");
        }
        else{
            return ResponseEntity.status(200).body(returnedAccount);
        }
        
    } 
       
    @PostMapping(value = "/login")      // 2
    public ResponseEntity loginAccount(@RequestBody Account login){
        Account returnedAccount = new Account();
        returnedAccount = accountService.loginAccount(login);
        if(returnedAccount == null){
            return ResponseEntity.status(401).body("Unauthorized"); 
        }
        if(returnedAccount.getPassword().equals(login.getPassword())){
            return ResponseEntity.status(200).body(returnedAccount);
        }
        else{
            return ResponseEntity.status(401).body("Unauthorized");
        }
        
    }

    @PostMapping(value = "/messages")   // 3
    public ResponseEntity postMessage(@RequestBody Message message){
        Message returnedMessage = new Message();
        returnedMessage = messageService.postMessage(message);
        if(returnedMessage == null){
            return ResponseEntity.status(400).body("Client error");
        }
        return ResponseEntity.status(200).body(returnedMessage);
    }

    @GetMapping("/messages")            // 4
    public ResponseEntity getMessage(){
        List<Message> returnedMessage = new ArrayList<>();
        returnedMessage = messageService.getMessages();
        return ResponseEntity.status(200).body(returnedMessage);
    }

    @GetMapping("/messages/{messageId}")// 5
    public ResponseEntity getMessageById(@PathVariable int messageId){
        Message returnedMessage = new Message();
        returnedMessage = messageService.getMessageById(messageId);
        return ResponseEntity.status(200).body(returnedMessage);
        
    }

    @DeleteMapping("/messages/{messageId}")// 6
    public ResponseEntity deleteMessageById(@PathVariable int messageId){
        boolean deleted;
        deleted = messageService.deleteMessageById(messageId);
        if(deleted){
            return ResponseEntity.status(200).body(1);
        }else{
            return ResponseEntity.status(200).body("");
        }
        
    }

    @PatchMapping("/messages/{messageId}")// 7
    public ResponseEntity patchMessageById(@PathVariable int messageId, @RequestBody Message messageText){
        Message returnedMessage = new Message();
        returnedMessage = messageService.patchMessage(messageText.getMessageText(), messageId);
        if(returnedMessage == null){
            return ResponseEntity.status(400).body("Client error");
        }else{
            return ResponseEntity.status(200).body(1);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")// 8
    public ResponseEntity getMessagesByAccount(@PathVariable int accountId){
        List<Message> returnedMessages = new ArrayList<>();
        returnedMessages = messageService.getMessagesByAccount(accountId);

        return ResponseEntity.status(200).body(returnedMessages);
        
    }


}
