package com.customermanagment.sims.service.inventory;

import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.repository.order.OrderProductRepository;
import com.customermanagment.sims.repository.order.OrderRepository;
import com.customermanagment.sims.repository.product.BrandRepository;
import com.customermanagment.sims.repository.product.ProductRepository;
import com.customermanagment.sims.utility.Utility;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * Inventory  Service implementation.
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Service
public class InventoryServiceImplementation implements InventoryService {

    Utility utility = new Utility();

    final BrandRepository brandRepository;

    final ProductRepository productRepository;

    final OrderRepository orderRepository;

    final OrderProductRepository orderProductRepository;

    /**
     * service constructor
     * @param brandRepository
     * @param productRepository
     * @param orderRepository
     * @param orderProductRepository
     */
    public InventoryServiceImplementation(BrandRepository brandRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    /**
     * get brands
     *
     * @return
     */
    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    /**
     * creates brand
     *
     * @param brand
     * @return
     */
    @Override
    public long createBrand(Brand brand) {
        return brandRepository.save(brand).getId();
    }


    /**
     * deletes brand
     * @param id
     */
    @Override
    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }

    /**
     * get brand by id
     * @param id
     * @return
     */
    @Override
    public Brand getBrandById(long id) {
        return brandRepository.findById(id).get();
    }

    /**
     * create or update product
     *
     * @param product
     * @return
     */
    @Override
    public long createProduct(Product product) {
        return productRepository.save(product).getId();
    }

    /**
     * deletes product by id
     * @param id
     */
    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    /**
     * get product by id
     * @param productId
     * @return
     */
    @Override
    public Product getProductById(long productId) {
        return productRepository.findById(productId).get();
    }

    /**
     * get all products
     *
     * @return
     */
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * get brand by product id
     * @param id
     * @return
     */
    @Override
    public Brand GetBrandByProductId(long id) {
        Product product = productRepository.findById(id).get();
        Brand brand = getBrandById(product.getBrand());
        return brand;
    }

    /**
     * get all products by brand id
     *
     * @param brandId
     * @return
     */
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

    /**
     * get available products
     *
     * @return
     */
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

    /**
     * calculate inventory value
     * @return
     */
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

    /**
     * calculate inventory by brand id
     * @param brandId
     * @return
     */
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

    /**
     * delete all inventory
     */
    @Override
    public void deleteAllInventory() {
        utility.deleteInventory(brandRepository, productRepository, orderRepository, orderProductRepository);
    }

    /**
     * insert dummy inventory
     */
    @Override
    public void insertInventory() {
        utility.insertInventory(brandRepository, productRepository);
    }
}