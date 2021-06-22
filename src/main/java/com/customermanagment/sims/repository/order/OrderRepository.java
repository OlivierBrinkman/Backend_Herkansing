package com.customermanagment.sims.repository.order;
import com.customermanagment.sims.model.tables.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order Repository
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
