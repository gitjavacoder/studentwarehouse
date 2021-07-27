package com.studentwarehouse.jms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SendMessage {
    @Autowired
    JmsOperations jmsOperations;



    //@PostConstruct runs when the server loads Servle and will only be executed once by the server. @PreDestroy executes after the execution of the destroy() method.
    //@PostConstruct
    //@Scheduled(fixedRate = 5000)
    public void send(String message){
        System.out.println("Start sending a message Start! ");
        long startTime = System.currentTimeMillis();
        //String message = XML_MSG;

        //for(long i=0; i< 1000; i++) {
            jmsOperations.convertAndSend("DEV.QUEUE.1", message);
        //}
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("End sending a message, Time: "+estimatedTime);
    }

   // @Async
    //public void send(String message){
   //     jmsOperations.convertAndSend("DEV.QUEUE.1", message);
    // }
}