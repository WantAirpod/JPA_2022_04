package com.cjy9249.orderShop.controller.user;

import com.cjy9249.orderShop.common.Response;
import com.cjy9249.orderShop.domain.entity.User;
import com.cjy9249.orderShop.service.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @GetMapping("info")
    public ResponseEntity<Response<User>> getUserInfo(
            @RequestParam(value = "userId") Long userId
    ) {
        return ResponseEntity.ok(
                Response.of(
                        userFacade.getUserInfo(userId),
                        "불러오기 완료"
                )
        );
    }
}
