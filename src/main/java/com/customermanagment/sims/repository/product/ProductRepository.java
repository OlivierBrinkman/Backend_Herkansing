package com.customermanagment.sims.repository.product;
import com.customermanagment.sims.model.tables.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}