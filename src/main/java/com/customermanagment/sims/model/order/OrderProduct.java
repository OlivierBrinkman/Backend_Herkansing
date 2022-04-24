package com.customermanagment.sims.model.order;
import javax.persistence.*;

@Entity
@Table(name = "ORDER_PRODUCTS")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "ORDER_ID")
    private long orderId;

    public OrderProduct(long id, long productId, long orderId) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderProduct() {
    }

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

    @Override
    public String toString() {
        return "Order_Product{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                '}';
    }
}
