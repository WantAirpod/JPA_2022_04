package com.cjy9249.orderShop.service.order;

import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.domain.entity.User;
import com.cjy9249.orderShop.domain.repository.order.OrderRepository;
import com.cjy9249.orderShop.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NotBoundException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Order getOrderInfo(Long orderId){
        return orderRepository.findByOrderId(orderId);
    }

    /**
     * 주문번호(자동생성), 회원아이디, 제품명, 결제일시(자동생성)
     * @param userId
     * @return
     * @throws NotBoundException
     */
    @Transactional
    public Long order(Long userId,String orderSrl,Long orderId, String productName) {
        //엔티티 조회
        User user = userRepository.findByUserId(userId);
        //주문 생성
        Order order = Order.createOrder(userId, orderSrl, orderId, productName);
        //주문 저장
        orderRepository.save(order);
        return order.getOrderId();
    }


}
