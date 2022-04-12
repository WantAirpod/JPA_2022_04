package com.cjy9249.orderShop.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class OrderRequestDto {
    @Size(max = 100, message = "100 자리 숫자 이하로 입력하세요.")
    @ApiModelProperty(example = "전기장판")
    private String productName;
}
