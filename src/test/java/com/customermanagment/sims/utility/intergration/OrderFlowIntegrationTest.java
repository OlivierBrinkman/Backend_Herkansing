package com.customermanagment.sims.utility.intergration;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.order.Order;
import com.customermanagment.sims.model.tables.order.OrderProduct;
import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderFlowIntegrationTest {

    Customer customer = new Customer(20, "John Doe", "JohnDoe@gmail.com", "0651477696", "10-05-2021");
    Order order = new Order();
    OrderProduct orderProduct1 = new OrderProduct();
    OrderProduct orderProduct2 = new OrderProduct();
    Brand brand = new Brand(1, "Apple");
    Product product1 = new Product(100, 1, "iPhone X", "Jet Black - 256GB", 1000, 10);
    Product product2 = new Product(200, 1, "iPhone XS", "Jet Black - 256GB", 1000, 10);
    List<Product> products = new ArrayList<>();


    @Test
    public void TestOrderFlow() {

        products.add(product1);
        products.add(product2);

        order.setId(1);
        order.setCustomerId(customer.getId());
        order.setDateCreated("05-10-2021");

        orderProduct1.setId(1000);
        orderProduct1.setOrderId(order.getId());
        orderProduct1.setProductId(product1.getId());

        orderProduct2.setId(2000);
        orderProduct2.setOrderId(order.getId());
        orderProduct2.setProductId(product2.getId());

        for (Product product : products) {
            order.setTotalPrice(order.getTotalPrice() + product.getPrice());
        }

        Assert.assertEquals(2000, order.getTotalPrice());
        products.remove(products.get(0));
        order.setTotalPrice(0);

        for (Product product : products) {
            order.setTotalPrice(order.getTotalPrice() + product.getPrice());
        }

        Assert.assertEquals(1000, order.getTotalPrice());
        Assert.assertEquals(true, order.getCustomerId() == customer.getId());
    }
}
