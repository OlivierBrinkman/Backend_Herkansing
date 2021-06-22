package com.customermanagment.sims.repository.order;

import com.customermanagment.sims.model.tables.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order_Product Repository
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
