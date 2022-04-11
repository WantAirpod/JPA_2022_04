package com.cjy9249.orderShop.controller.order;

import com.cjy9249.orderShop.common.Response;
import com.cjy9249.orderShop.domain.dto.OrderRequestDto;
import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.domain.repository.order.OrderRepository;
import com.cjy9249.orderShop.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @ApiOperation(value = "단일 회원 주문 조회", notes = "회원의 이메일로 주문 목록을 조회합니다.")
    @GetMapping("info")
    public ResponseEntity<Response<List<Order>>> getUserInfo(
            @RequestParam(value = "email") String email
    ) {
        return ResponseEntity.ok(
                Response.of(
                        orderService.getOrderInfo(email),
                        "불러오기 완료"
                )
        );
    }
    @ApiOperation(value = "주문", notes = "주문하기")
    @PostMapping(value = "order-product", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Order(@RequestBody OrderRequestDto request) {
        orderService.order(request);
        return "redirect:/orders";
    }

    @ApiOperation(value = "전체 회원 주문 목록 조회", notes = "전체 회원의 주문 목록을 조회한다.")
    @GetMapping("/user-list")
    public Page<Order> getAllUsers() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return orderRepository.findAll(pageRequest);
        //return orderRepository.findAllByOrderByOrderDtDesc(pageRequest);
    }

}
