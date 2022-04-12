package com.cjy9249.orderShop.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
public class JwtRequestDto {
    @ApiModelProperty(example = "cjy9249@naver.com")
    private String email;
    @ApiModelProperty(example = "cjy9249@CJY9249")
    private String password;
}
