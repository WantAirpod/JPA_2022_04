package com.cjy9249.orderShop.domain.repository.user;

import com.cjy9249.orderShop.domain.entity.Order;

public interface UserRepositoryCustom {
    void saveOrderInfo(Order order);
    Order getOrderInfo(Long id);

    //검색용
    //public List<Order> findAll(OrderSearch orderSearch);


}
