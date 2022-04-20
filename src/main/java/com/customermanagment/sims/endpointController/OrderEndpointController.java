package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.structures.OrderSummaryStructure;
import com.customermanagment.sims.model.tables.order.Order;
import com.customermanagment.sims.model.tables.order.OrderProduct;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderEndpointController {

    private final OrderServiceImplementation service;

    public OrderEndpointController(OrderServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/orders/all")
    public List<Order> getAllOrders() {
        return service.getOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderSummaryStructure getOrderDetails(@PathVariable long id) {
        return service.getSummaryStructure(id);
    }

    @PostMapping("/orders/create")
    public long createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @PutMapping("/orders/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable long id) {
        order.setId(id);
        Order updatedOrder = service.getOrderById(service.createOrder(order));
        return updatedOrder;
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable long id) {
        service.deleteOrderProductsByOrderId(id);
        return "Order and associated items have been removed";
    }

    @GetMapping("/orders/{id}/products")
    public List<Product> getProductsByOrderId(@PathVariable long id) {
        return service.getProductsByOrderId(id);
    }

    @GetMapping("/orders/{id}/price")
    public int getTotalPriceOrderById(@PathVariable long id) {
        int totalOrderPrice = 0;
        List<Product> orderProducts = service.getProductsByOrderId(id);
        for (Product product : orderProducts) {
            totalOrderPrice = totalOrderPrice + product.getPrice();
        }
        return totalOrderPrice;
    }

    @PostMapping("/orders/{id}/add/{productId}")
    public long addProductToOrder(@PathVariable long id, @PathVariable long productId) {
        OrderProduct productToAdd = new OrderProduct(service.getProductsByOrderId(id).size(), productId, id);
        return service.createOrderProduct(productToAdd);
    }

    @DeleteMapping("/orders/items/{orderProductId}")
    public String deleteOrderProductFromOrder(@PathVariable long orderProductId) {
        service.deleteProductByOrderId(orderProductId);
        return "Product have been removed from the order.";
    }

}
