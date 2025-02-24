package com.example.controller;

import org.jboss.logging.Messages;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        accountService = new AccountService(); 
        messageService = new MessageService();
    }
 

    @PostMapping(value = "/register")   // 1
    public ResponseEntity registerAccount(@RequestBody Account register){
        Account returnedAccount = new Account();
        returnedAccount = accountService.registerAccount(register);
        if(returnedAccount == null){
            return ResponseEntity.status(409).body("Conflict");
        }else if(returnedAccount.getAccountId() == null){
            return ResponseEntity.status(400).body("Client error");
        }
        else{
            return ResponseEntity.status(200).body(returnedAccount);
        }
        
    } 
       
    @PostMapping(value = "/login")      // 2
    public Account loginAccount(@RequestBody Account login){
        return login;
    }

    @PostMapping(value = "/messages")   // 3
    public Message postMessage(@RequestBody Message message){
        return message;
    }

    @GetMapping("/messages")            // 4
    public Message getMessage(){
        return null;
    }

    @GetMapping("/messages/{messageId}")// 5
    public Message getMessageById(@PathVariable String messageId){
        return null;
    }

    @DeleteMapping("/messages/{messageId}")// 6
    public int deleteMessageById(@PathVariable String messageId){
        return 0;
    }

    @PatchMapping("/messages/{messageId}")// 7
    public Message patchMessageById(@PathVariable String messageId){
        return null;
    }

    @GetMapping("/accounts/{accountId}/messages")// 8
    public Message getMessagesByAccount(@PathVariable String accountId){
        return null;
    }


}
