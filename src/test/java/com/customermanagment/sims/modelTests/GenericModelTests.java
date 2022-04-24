package com.customermanagment.sims.modelTests;

import com.customermanagment.sims.model.appUser.AppUser;
import com.customermanagment.sims.model.appUser.AppUserRole;
import com.customermanagment.sims.model.appUser.Roles;
import com.customermanagment.sims.model.customer.Customer;
import com.customermanagment.sims.model.customer.CustomerAddress;
import com.customermanagment.sims.model.order.Order;
import com.customermanagment.sims.model.product.Brand;
import com.customermanagment.sims.model.product.Product;
import org.junit.Assert;
import org.junit.Test;

public class GenericModelTests {

    AppUser appUser = new AppUser(123, "username", "password");
    AppUserRole appUserRole = new AppUserRole(1, 1, Roles.ADMIN.toString());
    Customer customer = new Customer(1, "John Doe", "JohnDoe@gmail.com", "0652147787", "01-01-2022");
    CustomerAddress customerAddress = new CustomerAddress(1, 123, "Lijnbaansgracht", "247", "1017Rl", "Amsterdam", "Netherlands");

    Brand brand = new Brand(1, "Apple");
    Product product = new Product(1, 1, "iPhone X", "256GB - Black", 1200, 10);
    Order order = new Order(1, 1, 2400, "01-01-2022");

    @Test
    public void testAppUser() throws Exception {
        Assert.assertNotNull(appUser.getId());
        Assert.assertNotNull(appUser.getUsername());
        Assert.assertNotNull(appUser.getPassword());

        Assert.assertNotNull(appUserRole.getId());
        Assert.assertNotNull(appUserRole.getUserId());
        Assert.assertNotNull(appUserRole.getUserRole());
    }

    @Test
    public void testCustomer() throws Exception {
        Assert.assertNotNull(customer.getId());
        Assert.assertNotNull(customer.getName());
        Assert.assertNotNull(customer.getEmail());
        Assert.assertNotNull(customer.getPhone());

        Assert.assertNotNull(customerAddress.getId());
        Assert.assertNotNull(customerAddress.getStreet());
        Assert.assertNotNull(customerAddress.getZipcode());
        Assert.assertNotNull(customerAddress.getNumber());
        Assert.assertNotNull(customerAddress.getCountry());
        Assert.assertNotNull(customerAddress.getCity());
    }

    @Test
    public void testInventory() throws Exception {
        Assert.assertNotNull(brand.getId());
        Assert.assertNotNull(brand.getName());
        Assert.assertNotNull(product.getId());
        Assert.assertNotNull(product.getBrand());
        Assert.assertNotNull(product.getPrice());
        Assert.assertNotNull(product.getPrice());
    }

    @Test
    public void testOrders() throws Exception {
        Assert.assertNotNull(order.getId());
        Assert.assertNotNull(order.getTotalPrice());
        Assert.assertNotNull(order.getCustomerId());
    }
}
