package com.customermanagment.sims.model.tables.customer;

import javax.persistence.*;
/**
 * Customer Entity
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CUSTOMER_SINCE")
    private String customerSince;

    //Constructors
    public Customer(long id, String name, String email, String phone, String customerSince) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.customerSince = customerSince;

    }

    public Customer() {
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCustomerSince(String customerSince) {
        this.customerSince = customerSince;
    }

    public String getCustomerSince() {
        return customerSince;
    }

    //To string
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", customer since ='" + customerSince + '\'' +
                '}';
    }
}
