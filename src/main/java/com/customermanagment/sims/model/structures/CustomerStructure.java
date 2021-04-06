package com.customermanagment.sims.model.structures;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.customer.CustomerAddress;

/**
 * Customer_Combined Entity
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public class CustomerStructure {

    //Attributes
    public Customer customer;
    public CustomerAddress customerAddress;

    //Constructor
    public CustomerStructure(Customer customer, CustomerAddress customerAddress) {
        this.customer = customer;
        this.customerAddress = customerAddress;
    }
}
