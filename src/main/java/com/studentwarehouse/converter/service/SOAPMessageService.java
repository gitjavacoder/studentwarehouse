package com.studentwarehouse.converter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.studentwarehouse.converter.model.Address;
import com.studentwarehouse.converter.model.Person;
import com.studentwarehouse.repository.AddressRepository;
import com.studentwarehouse.repository.PersonRepository;
import com.studentwarehouse.repository.entity.AddressEntity;
import com.studentwarehouse.repository.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SOAPMessageService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    public void saveMessage(String message){

        XmlMapper xmlMapper = new XmlMapper();
        Person person = null;
        try {
            person = xmlMapper.readValue(message, Person.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Address address = person.getAddress().get();
        List<AddressEntity> addressList = this.getAddresses(person);

        PersonEntity p = new PersonEntity();
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setAddressList(addressList);


        personRepository.save(p);

    }

    public List<AddressEntity> getAddresses(Person p){

        List<AddressEntity> addressList = new ArrayList<AddressEntity>();

        if(null != p.getAddress() && p.getAddress().size() > 0){
            List<Address> addresses = p.getAddress();

            for(Address a: addresses){
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setCity(a.getCity());
                addressEntity.setStreetName(a.getStreetName());
                addressEntity.setStreetNumber(a.getStreetNumber());
                addressList.add(addressEntity);
            }

        }
        return addressList;
    }
}
