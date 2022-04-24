package com.customermanagment.sims.utility;
import com.customermanagment.sims.model.customer.Customer;
import com.customermanagment.sims.model.customer.CustomerAddress;
import com.customermanagment.sims.model.order.Order;
import com.customermanagment.sims.model.order.OrderProduct;
import com.customermanagment.sims.model.product.Brand;
import com.customermanagment.sims.model.product.Product;
import com.customermanagment.sims.repository.customer.CustomerAddressRepository;
import com.customermanagment.sims.repository.customer.CustomerRepository;
import com.customermanagment.sims.repository.order.OrderProductRepository;
import com.customermanagment.sims.repository.order.OrderRepository;
import com.customermanagment.sims.repository.product.BrandRepository;
import com.customermanagment.sims.repository.product.ProductRepository;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Utility {

    public Utility() {}

    public void insertCustomers(CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {

        Customer customer1 = new Customer();customer1.setName("John Doe");customer1.setEmail("Johndoe@gmail.com");customer1.setPhone("0654944832");customer1.setCustomerSince("01/04/2019");
        Customer customer2 = new Customer();customer2.setName("Donald Duck");customer2.setEmail("Donald_100@live.nl");customer2.setPhone("0659488372");customer2.setCustomerSince("21/02/2018");
        Customer customer3 = new Customer();customer3.setName("Samantah Doe");customer3.setEmail("Samantah_Doe@hotmail.com");customer3.setPhone("0695844732");customer3.setCustomerSince("13/11/2020");

        long C1_Id = customerRepository.save(customer1).getId();
        long C2_Id =customerRepository.save(customer2).getId();
        long C3_Id = customerRepository.save(customer3).getId();

        CustomerAddress address1 = new CustomerAddress();address1.setCustomerId(C1_Id);address1.setStreet("Applestraat");address1.setNumber("10");address1.setZipcode("1029FG");address1.setCity("Enschede");address1.setCountry("Nederland");
        CustomerAddress address2 = new CustomerAddress();address2.setCustomerId(C2_Id);address2.setStreet("Banaanweg");address2.setNumber("115");address2.setZipcode("8675AC");address2.setCity("Amsterdam");address2.setCountry("Nederland");
        CustomerAddress address3 = new CustomerAddress();address3.setCustomerId(C3_Id);address3.setStreet("Sterrenlaan");address3.setNumber("14");address3.setZipcode("1254BV");address3.setCity("Haalem");address3.setCountry("Nederland");

        customerAddressRepository.save(address1);
        customerAddressRepository.save(address2);
        customerAddressRepository.save(address3);
    }

    public void insertInventory(BrandRepository brandRepository, ProductRepository productRepository) {
        Brand apple = new Brand();apple.setName("Apple");
        Brand samsung = new Brand();samsung.setName("Samsung");

        brandRepository.save(apple).getId();
        brandRepository.save(samsung).getId();

        Product product1 = new Product();product1.setName("iPhone 12 Mini");product1.setDescription("128GB - Ocean Blue");product1.setAmount(100);product1.setPrice(799);product1.setBrand(apple.getId());
        Product product2 = new Product(); product2.setName("iPhone 12 Pro"); product2.setDescription("512Gb - Deep Black"); product2.setAmount(100); product2.setPrice(999); product2.setBrand(apple.getId());
        Product product3 = new Product();product3.setName("Galaxy S20+");product3.setDescription("128GB - Grey");product3.setAmount(100);product3.setPrice(899);product3.setBrand(samsung.getId());
        Product product4 = new Product();product4.setName("Galaxy S21 Ultra");product4.setDescription("1TB - Deep Titanium");product4.setAmount(100);product4.setPrice(1199);product4.setBrand(samsung.getId());

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }

    public void deleteInventory(BrandRepository brandRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        deleteOrders(orderRepository, orderProductRepository);
        productRepository.deleteAll();
        brandRepository.deleteAll();
    }

    public void insertOrders(List<Customer> customers, List<Product> products, OrderProductRepository orderProductRepository, OrderRepository orderRepository, OrderServiceImplementation orderService) throws Exception {
        for(Customer customer : customers)
        {
            Order order = new Order();
            order.setCustomerId(customer.getId());
            order.setDateCreated(getCurrentDate());
            order.setTotalPrice(0);
            long orderId = orderRepository.save(order).getId();

            for(Product product : products)
            {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrderId(orderId);
                orderProduct.setProductId(product.getId());
                orderProductRepository.save(orderProduct);
            }
            order.setTotalPrice((int)calculateTotalByOrderId(orderId, orderService));
            orderRepository.save(order);
        }
    }

    public void deleteOrders(OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        orderProductRepository.deleteAll();
        orderRepository.deleteAll();
    }

    public String getCurrentDate() {
        Date temp_now =  java.sql.Date.valueOf(java.time.LocalDate.now());
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        return DateFor.format(temp_now);
    }

    public double calculateTotalByOrderId(long orderId, OrderServiceImplementation orderService) throws Exception {
        Order order = orderService.getOrderById(orderId);
        List<Product> products = orderService.getProductsByOrderId(orderId);
        double totalPrice = 0;
        for(Product p : products)
        {
            totalPrice = totalPrice + p.getPrice();
        }
        return totalPrice;
    }

    public double calculateTotalExVat(double total) {
        return  total / 1.21;
    }

    public double calculateVAT(double value) {
        return  value - calculateTotalExVat(value);
    }
}
