package com.cjy9249.orderShop.domain.repository.order;

import com.cjy9249.orderShop.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer>, OrderRepositoryCustom {
    Order findByOrderId(int orderId);
}
