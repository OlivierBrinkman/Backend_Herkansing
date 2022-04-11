package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.order.Order;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderEndpointController {

    //Service initialization
    private final OrderServiceImplementation service;

    //Constructor
    public OrderEndpointController(OrderServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/orders/all")
    public List<Order> getAllOrders() {
        return service.getOrders();
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

    @GetMapping("/orders/{id}/price")
    public int getTotalPriceOrderById(@PathVariable long id) {
        int totalOrderPrice = 0;
        return totalOrderPrice;
    }
}
