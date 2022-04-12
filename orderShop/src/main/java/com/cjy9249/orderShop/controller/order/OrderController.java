package com.cjy9249.orderShop.controller.order;

import com.cjy9249.orderShop.common.Response;
import com.cjy9249.orderShop.domain.dto.OrderRequestDto;
import com.cjy9249.orderShop.domain.entity.Order;
import com.cjy9249.orderShop.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by cjy9249
 * 단일 회원 주문 조회 / 전체 회원 주문 목록 조회 / 주문하기 기능 Controller
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ApiOperation(value = "단일 회원 주문 조회", notes = "회원의 이메일로 주문 목록을 조회합니다.")
    @GetMapping("info")
    public ResponseEntity<Response<List<Order>>> orderList(
            @RequestParam(value = "email") String email
    ) {
        return ResponseEntity.ok(
                Response.of(
                        orderService.findOrderInfo(email),
                        "불러오기 완료"
                )
        );
    }
    @ApiOperation(value = "주문", notes = "주문하기")
    @PostMapping(value = "order-product")
    public String orderProduct(@RequestBody OrderRequestDto request) {
        return orderService.order(request);
    }

    @ApiOperation(value = "전체 회원 주문 목록 조회", notes = "전체 회원의 주문 목록을 조회한다.")
    @GetMapping("user-list")
    public Page<Order> getAllUsers() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return orderService.findUsers(pageRequest);
    }

    @ApiOperation(value = "마지막 주문 정보 조회", notes = "이름, 이메일을 이용하여 검색 기능 및 마지막 주문 정보를 조회한다.")
    @GetMapping(value = "orderDetail")
    public List<Order> orderDetail(@RequestParam(value = "email") String email, @RequestParam(value = "name") String name) {
        return orderService.findLastOrder(email,name);
    }

}
