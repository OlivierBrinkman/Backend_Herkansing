package com.customermanagment.sims.model.structures;
/**
 * Product_Summary Entity
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public class ProductStructure {

    //ATTRIBUTES
    private Long ID;

    private String name;

    private String description;

    private String price;

    private String brand;

    //CONSTRUCTORS
    public ProductStructure(Long ID, String name, String description, String price, String brand) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    public ProductStructure() {
    }

    //GETTERS AND SETTERS
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    //To string
    @Override
    public String toString() {
        return "Product_Summary{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}