package com.customermanagment.sims.service.order;

import com.customermanagment.sims.model.structures.OrderSummaryStructure;
import com.customermanagment.sims.model.structures.ProductStructure;
import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.order.Order;
import com.customermanagment.sims.model.tables.order.OrderProduct;
import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.repository.customer.CustomerAddressRepository;
import com.customermanagment.sims.repository.customer.CustomerRepository;
import com.customermanagment.sims.repository.order.OrderProductRepository;
import com.customermanagment.sims.repository.order.OrderRepository;
import com.customermanagment.sims.repository.product.BrandRepository;
import com.customermanagment.sims.repository.product.ProductRepository;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
import com.customermanagment.sims.utility.Utility;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * Order Service implementation.
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Service
public class OrderServiceImplementation implements OrderService {

    Utility utility = new Utility();

    final OrderRepository orderRepository;
    final OrderProductRepository orderProductRepository;
    final InventoryServiceImplementation inventoryService;
    final BrandRepository brandRepository;
    final ProductRepository productRepository;
    final CustomerRepository customerRepository;
    final CustomerAddressRepository customerAddressRepository;

    /**
     * service constructor
     * @param orderRepository
     * @param orderProductRepository
     * @param inventoryService
     * @param customerRepository
     * @param customerAddressRepository
     * @param brandRepository
     * @param productRepository
     */
    public OrderServiceImplementation(OrderRepository orderRepository, OrderProductRepository orderProductRepository, InventoryServiceImplementation inventoryService, CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository, BrandRepository brandRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.inventoryService = inventoryService;
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    /**
     * creates order
     *
     * @param order
     */
    @Override
    public long createOrder(Order order) {
        return orderRepository.save(order).getId();
    }

    /**
     * deletes order by id
     * @param orderId
     */
    @Override
    public void deleteOrder(long orderId) {
        deleteOrderProductsByOrderId(orderId);
        orderRepository.deleteById(orderId);
    }

    /**
     * get order by id
     * @param orderId
     * @return
     */
    @Override
    public Order getOrderById(long orderId) {
        return orderRepository.findById(orderId).get();
    }

    /**
     * get all orders
     *
     * @return
     */
    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    /**
     * creates order product
     * @param orderProduct
     */
    @Override
    public void createOrderProduct(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
        Product p = inventoryService.getProductById(orderProduct.getProductId());
        p.setAmount(p.getAmount() - 1);
        inventoryService.createProduct(p);
    }

    /**
     * delete order products by order id
     * @param orderId
     */
    @Override
    public void deleteOrderProductsByOrderId(long orderId) {
        List<OrderProduct> allOrderProducts = orderProductRepository.findAll();
        Order order = getOrderById(orderId);

        for (OrderProduct op : allOrderProducts) {
            if (op.getOrderId() == orderId) {
                orderProductRepository.deleteById(op.getId());
            }
        }
    }

    /**
     * get products by order id
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> getProductsByOrderId(long orderId) {
        List<Product> products = new ArrayList<>();

        for (OrderProduct op : orderProductRepository.findAll()) {
            if (op.getOrderId() == orderId) {
                try {
                    Product p = inventoryService.getProductById(op.getProductId());
                    products.add(p);
                } catch (Exception e) {
                    return null;
                }

            }
        }
        return products;
    }

    /**
     * get product summary by product id
     * @param productId
     * @return
     */
    @Override
    public ProductStructure getProductSummary(long productId) {
        Product product = inventoryService.getProductById(productId);
        Brand brand = inventoryService.GetBrandByProductId(productId);
        ProductStructure productSummary = new ProductStructure();

        String price = String.valueOf(product.getPrice());

        productSummary.setID(product.getId());
        productSummary.setName(product.getName());
        productSummary.setDescription(product.getDescription());
        productSummary.setPrice("€" + price + ",-");
        productSummary.setBrand(brand.getName());

        return productSummary;
    }

    /**
     * get products by order id for order summary
     *
     * @param orderId
     * @return
     */
    @Override
    public List<ProductStructure> getProductsForSummary(long orderId) {
        List<ProductStructure> productSummaries = new ArrayList<>();
        List<Product> products;
        long counter = 1;

        try {
            products = getProductsByOrderId(orderId);
            for (Product product : products) {
                ProductStructure productSummary = getProductSummary(product.getId());
                productSummary.setID(counter);
                productSummaries.add(productSummary);
                counter++;
            }
        } catch (Exception e) {
        }

        return productSummaries;
    }

    /**
     * get structure for order summary
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public OrderSummaryStructure getSummaryStructure(long orderId) throws Exception {
        NumberFormat formatter = new DecimalFormat("#0.00");
        OrderSummaryStructure orderSummaryStructure = new OrderSummaryStructure();
        Order order = orderRepository.findById(orderId).get();
        int productCounter = getProductsByOrderId(orderId).size();

        orderSummaryStructure.setTotalPrice(order.getTotalPrice());
        orderSummaryStructure.setExVAT(formatter.format(utility.calculateTotalExVat(order.getTotalPrice())));
        orderSummaryStructure.setVAT(formatter.format(utility.calculateVAT(order.getTotalPrice())));
        orderSummaryStructure.setAmountProducts(productCounter);
        orderSummaryStructure.setNewInventory(inventoryService.calculateInventoryValue());

        return orderSummaryStructure;
    }

    /**
     * deletes all orders
     */
    @Override
    public void deleteOrders() {
        utility.deleteOrders(orderRepository, orderProductRepository);
    }

    /**
     * inserts dummy orders
     * @throws Exception
     */
    @Override
    public void insertOrders() throws Exception {
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = inventoryService.getProducts();

        if (customers.isEmpty()) {
            utility.insertCustomers(customerRepository, customerAddressRepository);
        }

        if (products.isEmpty()) {
            utility.insertInventory(brandRepository, productRepository);
        }

        utility.insertOrders(customers, products, orderProductRepository, orderRepository, this);
    }

    /**
     * Deletes all incomplete orders
     *
     * @throws Exception
     */
    @Override
    public void clearIncompleteOrders() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (order.getTotalPrice() == 0) {
                orderRepository.deleteById(order.getId());
            }
        }
    }
}