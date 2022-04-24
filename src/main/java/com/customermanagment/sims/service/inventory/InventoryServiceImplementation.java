package com.customermanagment.sims.service.inventory;

import com.customermanagment.sims.model.product.Brand;
import com.customermanagment.sims.model.product.Product;
import com.customermanagment.sims.repository.order.OrderProductRepository;
import com.customermanagment.sims.repository.order.OrderRepository;
import com.customermanagment.sims.repository.product.BrandRepository;
import com.customermanagment.sims.repository.product.ProductRepository;
import com.customermanagment.sims.utility.Utility;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImplementation implements InventoryService {

    Utility utility = new Utility();

    final BrandRepository brandRepository;

    final ProductRepository productRepository;

    final OrderRepository orderRepository;

    final OrderProductRepository orderProductRepository;

    public InventoryServiceImplementation(BrandRepository brandRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public long createBrand(Brand brand) {
        return brandRepository.save(brand).getId();
    }

    @Override
    public String deleteBrand(long id) {
        brandRepository.deleteById(id);
        return "Brand has been deleted";
    }

    @Override
    public Brand getBrandById(long id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public long createProduct(Product product) {
        return productRepository.save(product).getId();
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Brand GetBrandByProductId(long id) {
        Product product = productRepository.findById(id).get();
        Brand brand = getBrandById(product.getBrand());
        return brand;
    }

    @Override
    public List<Product> getProductsByBrandId(long brandId) {
        List<Product> productList = new ArrayList<>();

        for (Product p : productRepository.findAll()) {
            if (p.getBrand() == brandId) {
                productList.add(p);
            }
        }

        return productList;
    }

    @Override
    public List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>();

        for (Product product : productRepository.findAll()) {
            if (product.getAmount() > 0) {
                availableProducts.add(product);
            }
        }
        return availableProducts;
    }

    @Override
    public String calculateInventoryValue() {
        List<Product> allProducts = productRepository.findAll();
        int totalValue = 0;

        for (Product product : allProducts) {
            for (int i = 0; i < product.getAmount(); i++) {
                totalValue = totalValue + product.getPrice();
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        String formattedTotalPrice = decimalFormat.format(totalValue);

        return "€" + formattedTotalPrice;
    }

    @Override
    public String calculateInventoryValueByBrandId(long brandId) {
        List<Product> allProducts = getProductsByBrandId(brandId);
        int totalBrandValue = 0;

        for (Product product : allProducts) {
            for (int i = 0; i < product.getAmount(); i++) {
                totalBrandValue = totalBrandValue + product.getPrice();
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        String formattedTotalPrice = decimalFormat.format(totalBrandValue);

        return "€" + formattedTotalPrice;
    }

    @Override
    public void deleteAllInventory() {
        utility.deleteInventory(brandRepository, productRepository, orderRepository, orderProductRepository);
    }

    @Override
    public void insertInventory() {
        utility.insertInventory(brandRepository, productRepository);
    }
}