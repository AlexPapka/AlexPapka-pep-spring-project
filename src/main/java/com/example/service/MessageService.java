package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

import com.example.repository.MessageRepository;
import com.example.entity.Account;

import com.example.entity.Message;

@Service("MessageService")
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message){
        if(message.getMessageText() == "" || message.getMessageText().length() > 255){
            return null;
        }
        if(messageRepository.findAccountByMessageId(message.getPostedBy()) == null){
            return null;
        }else{
            return messageRepository.save(message);
        }
               
    }

    public List<Message> getMessages(){
        return messageRepository.getAllMessages();
    }

    public Message getMessageById(String messageId){
        return messageRepository.getMessageById(messageId);
    }

    
} 
