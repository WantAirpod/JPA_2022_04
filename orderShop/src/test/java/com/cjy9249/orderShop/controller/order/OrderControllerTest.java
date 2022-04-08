package com.cjy9249.orderShop.controller.order;

import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class OrderControllerTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private OrderService orderService;


    @Test
    public void 주문() throws Exception{
        orderService.order(123L,"1234", 1234L, "전기장판");
        Order order = orderService.getOrderInfo(1234L);
        //assertEquals(order.getProductName(),null);
    }

}