package com.customermanagment.sims.model.tables.customer;
import javax.persistence.*;
/**
 * Customer_Address Entity
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "CUSTOMER_ID")
    private long customerId;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;


    //Constructors
    public CustomerAddress(long id, long customerId, String street, String number, String zipcode, String city, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.customerId = customerId;
    }

    public CustomerAddress() {}

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    //To string
    @Override
    public String toString() {
        return "Customer_Address{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
