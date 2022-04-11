package com.cjy9249.orderShop.domain.repository.order;

import com.cjy9249.orderShop.domain.entity.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> getUserOrderList(String email, String name);
}
