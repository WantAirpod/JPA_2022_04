package com.cjy9249.orderShop.controller.order;

import com.cjy9249.orderShop.common.Response;
import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("info")
    public ResponseEntity<Response<Order>> getUserInfo(
            @RequestParam(value = "orderId") int orderId
    ) {
        return ResponseEntity.ok(
                Response.of(
                        orderService.getOrderInfo(orderId),
                        "불러오기 완료"
                )
        );
    }


}
