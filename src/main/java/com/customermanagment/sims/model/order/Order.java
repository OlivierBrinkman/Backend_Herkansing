package com.customermanagment.sims.model.order;
import javax.persistence.*;
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "CUSTOMER_ID")
    private long customerId;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Column(name = "DATE_CREATED")
    private String dateCreated;

    public Order(long id, long customerId, int totalPrice, String dateCreated) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", totalPrice=" + totalPrice +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }

}
