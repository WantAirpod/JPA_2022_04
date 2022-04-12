package com.cjy9249.orderShop.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LastOrderDto {
    @ApiModelProperty(example = "cjy9249@naver.com")
    private String email;
    @ApiModelProperty(example = "cjy")
    private String name;
}
