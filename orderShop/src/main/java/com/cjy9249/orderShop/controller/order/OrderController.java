package com.cjy9249.orderShop.controller.order;

import com.cjy9249.orderShop.common.Response;
import com.cjy9249.orderShop.domain.dto.OrderRequestDto;
import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
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
    @PostMapping(value = "orderProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Order(@RequestBody OrderRequestDto request) {
        orderService.order(request);
        return "redirect:/orders";
    }


}
