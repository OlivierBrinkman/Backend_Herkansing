package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.structures.OrderSummaryStructure;
import com.customermanagment.sims.model.tables.order.Order;
import com.customermanagment.sims.model.tables.order.OrderProduct;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class OrderEndpointController {

    private final OrderServiceImplementation service;

    public OrderEndpointController(OrderServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/orders/all")
    public ResponseEntity<List> getAllOrders() {
        return new ResponseEntity<>(service.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderSummaryStructure> getOrderDetails(@PathVariable long id) {
        return new ResponseEntity<>(service.getSummaryStructure(id), HttpStatus.OK);
    }

    @PostMapping("/orders/create")
    public ResponseEntity<Long> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(service.createOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Long> updateOrder(@RequestBody Order order, @PathVariable long id) {
        order.setId(id);
        return new ResponseEntity<>(service.createOrder(order), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable long id) {
        service.deleteOrderProductsByOrderId(id);
        return new ResponseEntity<>("Order and associated items have been removed", HttpStatus.OK);
    }

    @GetMapping("/orders/{id}/products")
    public ResponseEntity<List> getProductsByOrderId(@PathVariable long id) {
        return new ResponseEntity<>(service.getProductsByOrderId(id), HttpStatus.FOUND);
    }

    @GetMapping("/orders/{id}/price")
    public ResponseEntity<Integer> getTotalPriceOrderById(@PathVariable long id) {
        int totalOrderPrice = 0;
        List<Product> orderProducts = service.getProductsByOrderId(id);
        for (Product product : orderProducts) {
            totalOrderPrice = totalOrderPrice + product.getPrice();
        }
        return new ResponseEntity<>(totalOrderPrice, HttpStatus.OK);
    }

    @PostMapping("/orders/{id}/add/{productId}")
    public ResponseEntity<Long> addProductToOrder(@PathVariable long id, @PathVariable long productId) {
        OrderProduct productToAdd = new OrderProduct(service.getProductsByOrderId(id).size(), productId, id);
        return new ResponseEntity<>(service.createOrderProduct(productToAdd), HttpStatus.CREATED);
    }

    @DeleteMapping("/orders/items/{orderProductId}")
    public ResponseEntity<String> deleteOrderProductFromOrder(@PathVariable long orderProductId) {
        service.deleteProductByOrderId(orderProductId);
        return new ResponseEntity<>("Product have been removed from the order.", HttpStatus.OK);
    }

}
