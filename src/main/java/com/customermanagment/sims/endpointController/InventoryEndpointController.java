package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class InventoryEndpointController {

    //Service initialization
    private final InventoryServiceImplementation service;

    //Constructor
    public InventoryEndpointController(InventoryServiceImplementation service) {
        this.service = service;
    }

    //Brand Endpoints
    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        return service.getBrands();
    }

    @GetMapping("/brands/{id}")
    public Brand getBrandById(@PathVariable long id) {
        return service.getBrandById(id);
    }

    @PostMapping("/brands")
    public long createBrand(@RequestBody Brand brand) {
        return service.createBrand(brand);
    }

    @PutMapping("/brands/{id}")
    public Brand updateBrandById(@RequestBody Brand brand, @PathVariable long id) {
        brand.setId(id);
        service.deleteBrand(id);
        Brand updatedBrand = service.getBrandById(service.createBrand(brand));
        return updatedBrand;
    }

    @DeleteMapping("/brands/{id}")
    public String deleteBrandById(@PathVariable long id) {
        List<Product> products = service.getProductsByBrandId(id);
        for (Product product : products) {
            service.deleteProduct(product.getId());
        }
        service.deleteBrand(id);
        return "Brand and associated products have been removed";
    }

    //Product Endpoints
    @GetMapping("/brands/{id}/products/")
    public List<Product> getProductsByBrandId(@PathVariable long brandId) {
        return service.getProductsByBrandId(brandId);
    }

    @PostMapping("/brands/{id}/products/")
    public long createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/brands/{id}/products/{productId}")
    public Product updateProductById(@RequestBody Product product, @PathVariable long productId, @PathVariable long id) {
        product.setId(productId);
        Product updatedProduct = service.getProductById(service.createProduct(product));
        return updatedProduct;
    }

    @DeleteMapping("/brands/{id}/products/{productId}")
    public String deleteProductById(@PathVariable long id, @PathVariable long productId) {
        service.deleteProduct(id);
        return "Product have been removed";
    }

}