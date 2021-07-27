package com.studentwarehouse.jms.service;

import com.studentwarehouse.converter.service.SOAPMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

@Component
public class ReceiveMessage extends MessageListenerAdapter {
    @Autowired
    JmsOperations jmsOperations;

    @Autowired
    SOAPMessageService soapMessageService;

    //@Override
    @JmsListener(destination = "${project.mq.queue}")
    public void onMessage(Message message) {
        long startTime = System.currentTimeMillis();

        String messageBody = null;
        SOAPMessage soapMessage = null;
        try {
            messageBody = message.getBody(String.class);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Received message: " + messageBody);
        this.soapMessageService.saveMessage(messageBody);
    }


}