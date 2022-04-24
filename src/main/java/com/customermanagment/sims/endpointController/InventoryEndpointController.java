package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class InventoryEndpointController {

    private final InventoryServiceImplementation service;

    public InventoryEndpointController(InventoryServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/brands/all")
    public ResponseEntity<List> getAllBrands() {
        return new ResponseEntity<>(service.getBrands(), HttpStatus.OK);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable long id) {
        return new ResponseEntity<>(service.getBrandById(id), HttpStatus.FOUND);
    }

    @PostMapping("/brands/create")
    public ResponseEntity<Long> createBrand(@RequestBody Brand brand) {
        return new ResponseEntity<>(service.createBrand(brand), HttpStatus.CREATED);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<Long> updateBrandById(@RequestBody Brand brand, @PathVariable long id) {
        brand.setId(id);
        return new ResponseEntity<>(service.createBrand(brand), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<String> deleteBrandById(@PathVariable long id) {
        List<Product> products = service.getProductsByBrandId(id);
        for (Product product : products) {
            service.deleteProduct(product.getId());
        }
        service.deleteBrand(id);
        return new ResponseEntity<>("Brand and associated products have been removed", HttpStatus.OK);
    }

    @GetMapping("/brands/{id}/products")
    public ResponseEntity<List> getProductsByBrandId(@PathVariable long id) {
        return new ResponseEntity<>(service.getProductsByBrandId(id), HttpStatus.FOUND);
    }

    @PostMapping("/brands/{id}/products")
    public ResponseEntity<Long> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/brands/{id}/products/{productId}")
    public ResponseEntity<Long> updateProductById(@RequestBody Product product, @PathVariable long productId, @PathVariable long id) {
        product.setId(productId);
        return new ResponseEntity<>(service.createProduct(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/brands/{id}/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable long id, @PathVariable long productId) {
        service.deleteProduct(id);
        return new ResponseEntity<>("Product have been removed", HttpStatus.OK);
    }
}