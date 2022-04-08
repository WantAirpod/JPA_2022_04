package com.cjy9249.orderShop.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long userId;
    private String orderSrl;
    private String productName;
}
