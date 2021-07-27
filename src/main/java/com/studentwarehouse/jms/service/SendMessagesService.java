package com.studentwarehouse.jms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.studentwarehouse.converter.model.Address;
import com.studentwarehouse.converter.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@EnableAsync
@Service
public class SendMessagesService {

    @Autowired
    SendMessage sendMessage;

    @Async
    @Scheduled(fixedRate = 60000)
    //@Scheduled(fixedRate = 100)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        //System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
        for(long i=0; i< 1000; i++) {
            sendMessage.send(generateJMSMessage());
        }
        //Thread.sleep(60000);
    }

    public String generateString(String name) {
        String uuid = UUID.randomUUID().toString();
        return name + " = " + uuid;
    }


    private String generateJMSMessage(){
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;

        List<String> phoneNumbers = new ArrayList<String>();
        List<Address> addresses = new ArrayList<Address>();

        for(int i = 0; i < new Random().nextInt(5); i++){
            phoneNumbers.add(generateString("Phone Number"));
        }

        Person p = new Person();
        p.setFirstName(generateString("First Name"));
        p.setLastName(generateString("Last Name"));
        p.setPhoneNumbers(phoneNumbers);

        for(int i = 0; i < new Random().nextInt(5); i++){
            Address address = new Address();
            address.setCity(generateString("City"));
            address.setStreetName(generateString("Street Name"));
            address.setStreetNumber(generateString("Street Number"));

            addresses.add(address);
        }

        p.setAddress(addresses);

        try {
            xml = xmlMapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
