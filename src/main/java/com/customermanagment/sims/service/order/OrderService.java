package com.customermanagment.sims.service.order;
import com.customermanagment.sims.model.tables.order.Order;
import com.customermanagment.sims.model.tables.order.OrderProduct;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.model.structures.ProductStructure;
import com.customermanagment.sims.model.structures.OrderSummaryStructure;
import java.util.List;
/**
 * Order Service.
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public interface OrderService {

     List<Order> getOrders();
     List<Product> getProductsByOrderId(long orderId) throws Exception;
     List<ProductStructure> getProductsForSummary(long orderId);
     ProductStructure getProductSummary(long productId);
     OrderSummaryStructure getSummaryStructure(long orderId) throws Exception;
     Order getOrderById(long orderId);

     void createOrder(Order order);
     void deleteOrder(long orderId);
     void createOrderProduct(OrderProduct orderProduct);
     void deleteOrderProductsByOrderId(long orderId);
     void deleteOrders();
     void insertOrders() throws Exception;
     void clearIncompleteOrders();
}
