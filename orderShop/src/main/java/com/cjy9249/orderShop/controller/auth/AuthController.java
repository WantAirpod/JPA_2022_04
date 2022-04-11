package com.cjy9249.orderShop.controller.auth;


import com.cjy9249.orderShop.domain.dto.JwtRequestDto;
import com.cjy9249.orderShop.domain.dto.MemberSignupRequestDto;
import com.cjy9249.orderShop.service.security.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "로그인", notes = "설정한 이메일과 비밀번호로 로그인 합니다.")
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody JwtRequestDto request) {
        try {
            authService.login(request);
            return request.getEmail() + " 님 반갑습니다.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃과 함께 페이지 페이지 접근 권한이 사라집니다.")
    @GetMapping("logout")
    public String logout(HttpServletRequest request , HttpServletResponse response) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return "로그아웃 되었습니다.";
        }
        return "로그인 해주세요.";
    }

    @ApiOperation(value = "회원 가입",
            notes = "")
    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody @Valid MemberSignupRequestDto request) {
        //Map<String, String> vaildatorResult = MemberService.validateHandling(errors);
        return authService.signup(request);
    }

}
