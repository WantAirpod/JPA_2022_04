package com.cjy9249.orderShop.service.order;

import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Order getOrderInfo(int orderId){
        return orderRepository.findByOrderId(orderId);
    }

}
