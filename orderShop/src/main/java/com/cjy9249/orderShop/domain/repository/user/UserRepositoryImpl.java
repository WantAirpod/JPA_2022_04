package com.cjy9249.orderShop.domain.repository.user;

import com.cjy9249.orderShop.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final EntityManager em;

    @Override
    public void saveOrderInfo(Order order) {
        em.persist(order);
    }

    @Override
    public Order getOrderInfo(Long id) {
        return em.find(Order.class,id);
    }

    //검색]


}
