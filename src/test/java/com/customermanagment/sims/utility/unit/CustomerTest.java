package com.customermanagment.sims.utility.unit;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.customer.CustomerAddress;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    Customer customer = new Customer();
    CustomerAddress customerAddress = new CustomerAddress();

    @Test
    public void testCustomer() {
        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("Johndoe@gmail.com");
        customer.setPhone("0543299384");
        customer.setCustomerSince("05-10-2021");

        customerAddress.setId(20);
        customerAddress.setCustomerId(customer.getId());
        customerAddress.setNumber("Apple Street");
        customerAddress.setNumber("1010");
        customerAddress.setCity("Amsterdam");
        customerAddress.setZipcode("1234AN");
        customerAddress.setCountry("Netherlands");


        Assert.assertEquals(1, customer.getId());


    }

}
