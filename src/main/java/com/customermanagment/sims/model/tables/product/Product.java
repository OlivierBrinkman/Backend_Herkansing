package com.customermanagment.sims.model.tables.product;
import javax.persistence.*;
/**
 * Product Entity
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {

    //Attributes
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "ID") private long id;

    @Column(name = "BRAND_ID") private long brandId;

    @Column(name = "NAME") private String name;

    @Column(name = "DESCRIPTION") private String description;

    @Column(name = "PRICE") private int price;

    @Column(name = "AMOUNT") private int amount;

    //Constructors
    public Product(long id, long brandId, String name, String description, int price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.brandId = brandId;
    }

    public Product() {
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getBrand() {
        return brandId;
    }

    public void setBrand(long brandId) {
        this.brandId = brandId;
    }


    public String getDisplayPrice(){
        return "€" + price;
    }

    //To string
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand=" + brandId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
