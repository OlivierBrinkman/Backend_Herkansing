package com.customermanagment.sims.utility.unit;

import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InventoryTest {

    Brand brand = new Brand();
    Product product1 = new Product();
    Product product2 = new Product();
    List<Product> products = new ArrayList<>();

    int totalStockValue = 0;
    int totalStockItems = 0;

    @Before
    public void setValues() {
        brand.setId(1);
        brand.setName("Apple");

        product1.setId(1);
        product1.setName("iPhone X");
        product1.setBrand(1);
        product1.setAmount(5);
        product1.setPrice(1000);

        product2.setId(2);
        product2.setName("iPhone XS");
        product2.setBrand(1);
        product2.setAmount(5);
        product2.setPrice(1000);

        products.add(product1);
        products.add(product2);

    }

    public void updateInventory() {
        totalStockValue = 0;
        totalStockItems = 0;
        for (Product p : products) {
            totalStockItems = totalStockItems + p.getAmount();
            for (int i = 0; i < p.getAmount(); i++) {
                totalStockValue = totalStockValue + p.getPrice();
            }
        }
    }

    @Test
    public void testInventory() {

        product1.setAmount(product1.getAmount() - 2);
        product2.setAmount(product2.getAmount() - 3);
        updateInventory();

        Assert.assertEquals(5, totalStockItems);
        Assert.assertEquals(5000, totalStockValue);


    }
}
