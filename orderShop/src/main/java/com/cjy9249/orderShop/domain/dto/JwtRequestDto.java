package com.cjy9249.orderShop.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDto {
    private String email;
    private String password;
}
