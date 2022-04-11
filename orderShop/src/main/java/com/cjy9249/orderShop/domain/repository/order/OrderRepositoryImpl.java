package com.cjy9249.orderShop.domain.repository.order;

import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.domain.entity.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom{
    public final JPAQueryFactory jpaQueryFactory;
    public final QOrder order = QOrder.order;

    public OrderRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Order> getUserOrderList(String email, String name) {
        return jpaQueryFactory.selectFrom(order)
                .where(
                        order.email.eq(email),
                        order.name.eq(name)
                )
                .orderBy(order.orderDt.desc())
                .limit(1)
                .fetch();
    }
}
