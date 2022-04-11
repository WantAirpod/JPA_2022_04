package com.cjy9249.orderShop.service.order;

import com.cjy9249.orderShop.domain.dto.OrderRequestDto;
import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


    /**
     * 단일회원의 주문목록 조회
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public List<Order> getOrderInfo(String email){
        return orderRepository.findByEmail(email);
    }

    /**
     * 주문번호(자동생성), 회원아이디, 제품명, 결제일시(자동생성)
     * @param request
     * @return
     */
    @Transactional
    public Long order(OrderRequestDto request) {
        //주문 생성
        Order order = Order.createOrder(request.getProductName());
        //주문 저장
        orderRepository.save(order);
        return order.getOrderId();
    }


}
