package com.cjy9249.orderShop.service.order;

import com.cjy9249.orderShop.common.ErrorCode;
import com.cjy9249.orderShop.common.exception.BaseException;
import com.cjy9249.orderShop.domain.dto.OrderRequestDto;
import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    /**
     * 단일회원의 주문목록 조회
     * @param email
     * @return
     */
    public List<Order> findOrderInfo(String email) throws BaseException{
        List<Order> findOrderInfoList = orderRepository.findByEmail(email);
        if(findOrderInfoList.isEmpty())
            throw new BaseException("해당하는 주문이 없습니다.", ErrorCode.COMMON_NOT_FOUND);
        return findOrderInfoList;
    }

    /**
     * 주문번호(자동생성), 회원아이디, 제품명, 결제일시(자동생성)
     * @param request
     * @return
     */
    @Transactional
    public String order(OrderRequestDto request) {
        //주문 생성
        Order order = Order.createOrder(request.getProductName());
        //주문 저장
        orderRepository.save(order);
        return order.getName() + " 님 " + order.getProductName() +" 주문이 완료 되었습니다.";
    }

    /**
     * 전체 회원 주문 목록 조회 (10 개씩 페이징 처리)
     * @param pageable
     * @return
     */
    public Page<Order> findUsers(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    /**
     * email, name 을 이용해 회원의 가장 최근 주문 정보 가져오기
     * @param email
     * @param name
     * @return
     */
    public List<Order> findLastOrder(String email, String name){
        List<Order> findLastOrder = orderRepository.getUserOrderList(email,name);
        if(findLastOrder.isEmpty())
            throw new BaseException("해당하는 주문이 없습니다.", ErrorCode.COMMON_NOT_FOUND);
        return findLastOrder;
    }
}
