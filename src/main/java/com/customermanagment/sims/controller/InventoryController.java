package com.customermanagment.sims.controller;
import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.repository.product.ProductRepository;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Inventory Controller
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Controller
public class InventoryController {

    long selectedBrandId = 0;
    final InventoryServiceImplementation inventoryService;
    final ProductRepository productRepository;

    /**
     * services constructor
     * @param inventoryService
     * @param productRepository
     */
    public InventoryController(InventoryServiceImplementation inventoryService, ProductRepository productRepository) {
        this.inventoryService = inventoryService;
        this.productRepository = productRepository;
    }

    /**
     * return inventory page
     *
     * @param model
     * @return
     */
    @GetMapping("/inventory")
    public String index(Model model) {
        model.addAttribute("brands", inventoryService.getBrands());
        model.addAttribute("totalValue", inventoryService.calculateInventoryValue());
        model.addAttribute("newBrand", new Brand());
        return "inventory/Brands";
    }

    /**
     * returns products by brand id
     *
     * @param brand
     * @param model
     * @return
     */
    @GetMapping(value = "/inventory/products")
    public String brandProducts(@RequestParam(name = "brand") long brand, Model model) {
        return navigateProducts(model, brand);
    }

    /**
     * navigates to new product page
     *
     * @param brandId
     * @param model
     * @return
     */
    @GetMapping(value = "/inventory/product/new")
    public String productNew(@RequestParam(name = "brandId") long brandId, Model model) {
        Product product = new Product();
        selectedBrandId = brandId;
        Brand selectedBrand = inventoryService.getBrandById(brandId);
        product.setBrand(selectedBrand.getId());
        model.addAttribute("newProduct", product);
        model.addAttribute("selectedBrand", inventoryService.getBrandById(brandId));
        System.out.println("Brand ID = " + brandId);
        return "inventory/ProductCreate";
    }

    /**
     * creates product
     *
     * @param product
     * @param model
     * @return
     */
    @PostMapping(value = "/inventory/product/new")
    public String productCreate(@ModelAttribute Product product, Model model) {
        inventoryService.createProduct(product);
        return navigateProducts(model, product.getBrand());
    }

    /**
     * navigates to Update page
     *
     * @param productId
     * @param model
     * @return
     */
    @GetMapping(value = "/inventory/product/edit")
    public String productSelect(@RequestParam(name = "productId") long productId, Model model) {
        model.addAttribute("selectedProduct", inventoryService.getProductById(productId));
        return "inventory/ProductUpdate";
    }

    /**
     * updates product
     *
     * @param product
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "/inventory/product/edit/{id}", consumes = "application/x-www-form-urlencoded")
    public String productUpdate(Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return navigateProducts(model, product.getBrand());
        }
        inventoryService.createProduct(product);
        return navigateProducts(model, product.getBrand());
    }

    /**
     * delets products
     *
     * @param product
     * @param model
     * @return
     */
    @RequestMapping(value = "/inventory/product/delete", method = RequestMethod.GET)
    public String productDelete(@RequestParam(name = "product") long product, Model model) {
        Product p = inventoryService.getProductById(product);
        long brandId = p.getBrand();
        inventoryService.deleteProduct(product);
        model.addAttribute("products", inventoryService.getProductsByBrandId(brandId));
        model.addAttribute("selectedBrand", inventoryService.getBrandById(brandId));
        return "inventory/Products";
    }

    /**
     * creates new brand
     *
     * @param brand
     * @param model
     * @return
     */
    @PostMapping("/inventory/brand/new")
    public String brandSave(@ModelAttribute Brand brand, Model model) {
        inventoryService.createBrand(brand);
        model.addAttribute("brands", inventoryService.getBrands());
        model.addAttribute("newBrand", new Brand());
        model.addAttribute("totalValue", inventoryService.calculateInventoryValue());
        return "inventory/Brands";
    }

    /**
     * deletes brand
     *
     * @param brand
     * @param model
     * @return
     */
    @RequestMapping(value = "/inventory/brand/delete", method = RequestMethod.GET)
    public String brandDelete(@RequestParam(name = "brand") long brand, Model model) {
        inventoryService.deleteBrand(brand);
        model.addAttribute("brands", inventoryService.getBrands());
        model.addAttribute("newBrand", new Brand());
        return "redirect:/inventory";
    }

    /**
     * deletes all inventory
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/inventory/delete/all")
    public String deleteInventory(Model model) {
        inventoryService.deleteAllInventory();
        model.addAttribute("brands", inventoryService.getBrands());
        model.addAttribute("newBrand", new Brand());
        model.addAttribute("totalValue", inventoryService.calculateInventoryValue());
        return "redirect:/inventory";
    }

    /**
     * inserts dummy data
     * @param model
     * @return
     */
    @GetMapping(value = "/inventory/insert/data")
    public String insertInventory(Model model) {
        inventoryService.insertInventory();
        model.addAttribute("brands", inventoryService.getBrands());
        model.addAttribute("newBrand", new Brand());
        model.addAttribute("totalValue", inventoryService.calculateInventoryValue());
        return "redirect:/inventory";
    }

    /**
     * navigates to products of brand
     * @param model
     * @param brand
     * @return
     */
    public String navigateProducts(Model model, long brand) {
        model.addAttribute("products", inventoryService.getProductsByBrandId(brand));
        model.addAttribute("selectedBrand", inventoryService.getBrandById(brand));
        model.addAttribute("brandValue", inventoryService.calculateInventoryValueByBrandId(brand));
        return "inventory/Products";
    }

}