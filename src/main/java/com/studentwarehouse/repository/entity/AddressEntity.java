package com.studentwarehouse.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "streetnumber")
    private String streetNumber;

    @Column(name = "streetname")
    private String streetName;

    @Column(name = "city")
    private String city;

    //@ManyToOne
    //@JoinColumn(name = "address_id", referencedColumnName = "id")
    //private PersonEntity personEntity;

    @ManyToMany(mappedBy = "addressList")
    List<PersonEntity> persons;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /*
    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }
    */

    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }
}
