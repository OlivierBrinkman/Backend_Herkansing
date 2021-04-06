package com.customermanagment.sims.service.inventory;
import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import java.util.List;
/**
 * Inventory Service.
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public interface InventoryService {
     List<Brand> getBrands();
     List<Product> getProducts();
     List<Product> getProductsByBrandId(long brandId);
     List<Product> getAvailableProducts();
     Brand getBrandById(long id);
     Brand GetBrandByProductId(long id);
     Product getProductById(long productId);
     String calculateInventoryValue();
     String calculateInventoryValueByBrandId(long brandId);
     long createBrand(Brand brand);
     long createOrUpdateProduct(Product product);
     void deleteProduct(long id);
     void deleteBrand(long id);
     void deleteAllInventory();
     void insertInventory();
}
