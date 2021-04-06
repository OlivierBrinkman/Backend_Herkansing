package com.customermanagment.sims.repository.product;
import com.customermanagment.sims.model.tables.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Brand Repository
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
