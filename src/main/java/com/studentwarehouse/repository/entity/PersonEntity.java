package com.studentwarehouse.repository.entity;

import com.studentwarehouse.converter.model.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    //@Column(name = "phonenumbers")
    //private List<String> phoneNumbers = new ArrayList<>();

    //@Column(name = "address")
    //private List<Address> address = new ArrayList<>();

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL)
    private List<AddressEntity> addressList;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    */


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AddressEntity> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressEntity> addressList) {
        this.addressList = addressList;
    }
}
