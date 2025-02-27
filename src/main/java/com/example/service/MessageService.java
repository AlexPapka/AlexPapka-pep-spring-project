package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

import javax.transaction.Transactional;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Account;

import com.example.entity.Message;

@Service("MessageService")
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Message postMessage(Message message){
        if(message.getMessageText() == "" || message.getMessageText().length() > 255){
            return null;
        }
        Account holdAccount = new Account();
        holdAccount = accountRepository.findAccountByMessageId(message.getPostedBy());
        if(holdAccount == null){
            return null;
        }
        return messageRepository.save(message);  
    }

    public List<Message> getMessages(){
        return messageRepository.getAllMessages();
    }

    public Message getMessageById(int messageId){
        return messageRepository.getMessageById(messageId);
    }

    @Transactional
    public boolean deleteMessageById(int messageId){
        if(messageRepository.getMessageById(messageId) == null){
            return false;
        }else{
            messageRepository.deleteMessageById(messageId);
            return true;
        }
        
    }

    public List<Message> getMessagesByAccount(int accountId){
        return messageRepository.findByAccountId(accountId);
    }

    @Transactional
    public Message patchMessage(String messageText, int messageId){
        if(messageText == "" || messageText.length() > 255){
            return null;
        }
        if(messageRepository.getMessageById(messageId) == null){
            return null;
        }else{
            messageRepository.patchMessageById(messageText, messageId);
            return messageRepository.getMessageById(messageId);            
        }
    }

    
} 
