package com.cjy9249.orderShop.controller.order;

import com.cjy9249.orderShop.common.Response;
import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("info")
    public ResponseEntity<Response<Order>> getUserInfo(
            @RequestParam(value = "orderId") Long orderId
    ) {
        return ResponseEntity.ok(
                Response.of(
                        orderService.getOrderInfo(orderId),
                        "불러오기 완료"
                )
        );
    }

    @PostMapping("/{userId}/{orderId}/{orderSrl}/{productName}")
    public String Order(@PathVariable("userId") Long userId,@PathVariable("orderSrl") String orderSrl,@PathVariable("orderId") Long orderId,@PathVariable("productName") String productName) {
        orderService.order(userId,orderSrl,orderId,productName);
        return "redirect:/orders";
    }


}
