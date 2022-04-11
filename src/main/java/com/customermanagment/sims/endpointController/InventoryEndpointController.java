package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryEndpointController {

    //Service initialization
    private final InventoryServiceImplementation service;

    //Constructor
    public InventoryEndpointController(InventoryServiceImplementation service) {
        this.service = service;
    }

    //Brand Endpoints
    @GetMapping("/brands/all")
    public List<Brand> getAllBrands() {
        return service.getBrands();
    }

    @PostMapping("/brands/create")
    public long createBrand(@RequestBody Brand brand) {
        return service.createBrand(brand);
    }

    @PutMapping("/brands/update/{id}")
    public Brand updateBrand(@RequestBody Brand brand, @PathVariable long id) {
        brand.setId(id);
        service.deleteBrand(id);
        Brand updatedBrand = service.getBrandById(service.createBrand(brand));
        return updatedBrand;
    }

    @DeleteMapping("/brands/delete/{id}")
    public String deleteBrand(@PathVariable long id) {
        List<Product> products = service.getProductsByBrandId(id);
        for (Product product : products) {
            service.deleteProduct(product.getId());
        }
        service.deleteBrand(id);
        return "Brand and associated products have been removed";
    }

    //Product Endpoints
    @GetMapping("/brands/{brandId}/products/all")
    public List<Product> getProductsByBrandId(@PathVariable long brandId) {
        return service.getProductsByBrandId(brandId);
    }

    //Hier is brandId niet nodig want die zit al in de request body die opgemaakt wordt op /products/all
    @PostMapping("/brands/{brandId}/products/all")
    public long createProduct(@RequestBody Product product, @PathVariable long brandId) {
        return service.createProduct(product);
    }

    @PutMapping("/brands/{brandId}/products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable long brandId, @PathVariable long id) {
        product.setId(id);
        Product updatedProduct = service.getProductById(service.createProduct(product));
        return updatedProduct;
    }

    @DeleteMapping("/brands/{brandId}/products/{id}")
    public String deleteProduct(@PathVariable long id) {
        service.deleteProduct(id);
        return "Product have been removed";
    }

}