package com.customermanagment.sims.model.tables.order;
import javax.persistence.*;

/**
 * Order_Product Entity
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
//ENTITY Order_Product
@Entity
@Table(name = "ORDER_PRODUCTS")
public class OrderProduct {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "ORDER_ID")
    private long orderId;

    //Constructors
    public OrderProduct(long id, long productId, long orderId) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderProduct() {
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Order_Product{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                '}';
    }
}
