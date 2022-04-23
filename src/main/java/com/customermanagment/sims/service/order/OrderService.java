package com.customermanagment.sims.service.order;

import com.customermanagment.sims.model.structures.OrderSummaryStructure;
import com.customermanagment.sims.model.structures.ProductStructure;
import com.customermanagment.sims.model.tables.order.Order;
import com.customermanagment.sims.model.tables.order.OrderProduct;
import com.customermanagment.sims.model.tables.product.Product;

import java.util.List;

/**
 * Order Service.
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
public interface OrderService {

     List<Order> getOrders();

     List<Product> getProductsByOrderId(long orderId);

     List<ProductStructure> getProductsForSummary(long orderId);

     ProductStructure getProductSummary(long productId);

     OrderSummaryStructure getSummaryStructure(long orderId);

    Order getOrderById(long orderId);

    long createOrder(Order order);

    String deleteOrder(long orderId);

    long createOrderProduct(OrderProduct orderProduct);

    String deleteOrderProductsByOrderId(long orderId);

    String deleteProductByOrderId(long orderProductId);

    String deleteOrders();

    void insertOrders() throws Exception;
}
