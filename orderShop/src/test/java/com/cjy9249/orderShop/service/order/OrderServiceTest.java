package com.cjy9249.orderShop.service.order;

import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.domain.repository.order.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("상품 등록 및 조회 테스트")
    public void insertProductTest() {
        Order order = Order.builder()
                .name("cjy").
                productName("전기장판").
                email("cjy9249@naver.com").
                orderDt(LocalDateTime.now()).
                build();

        orderRepository.save(order);
        List<Order> testOrder = orderRepository.findByEmail("cjy9249@naver.com");
        Assertions.assertEquals(testOrder.size(),1);
    }
    @Test
    @DisplayName("마지막 상품 조회 테스트")
    public void findLastProductTest() {
        Order order = Order.builder()
                .name("cjy").
                productName("전기장판").
                email("cjy9249@naver.com").
                orderDt(LocalDateTime.now()).
                build();

        orderRepository.save(order);
        List<Order> lastOrder = orderService.findLastOrder("cjy9249@naver.com", "cjy");
        Assertions.assertEquals(lastOrder.size(),1);
    }

    @Test
    @DisplayName("모든 주문 목록 조회 테스트")
    public void findAllUsers() {
        Order order = Order.builder()
                .name("cjy").
                productName("전기장판").
                email("cjy9249@naver.com").
                orderDt(LocalDateTime.now()).
                build();

        Order order2 = Order.builder()
                .name("cjy2").
                productName("전기장판담요").
                email("cjy92491@naver.com").
                orderDt(LocalDateTime.now()).
                build();
        orderRepository.save(order);
        orderRepository.save(order2);

        List<Order> findAllOrder = orderRepository.findAll();
        Assertions.assertEquals(findAllOrder.size(),2);
    }

}