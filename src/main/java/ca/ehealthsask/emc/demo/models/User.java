package ca.ehealthsask.emc.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "PERSONS")
public class User{

    @Id
    @GeneratedValue
    private int personID;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;


    protected User(){
    }

    public User(int id, String ln, String fn, String ad, String C){
        super();
        this.personID = id;
        this.lastName = ln;
        this.firstName = fn;
        this.address = ad;
        this.city = C;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

