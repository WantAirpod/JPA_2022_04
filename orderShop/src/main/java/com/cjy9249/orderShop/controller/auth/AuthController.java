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

/**
 * created by cjy9249
 * 로그인 / 로그아웃 / 회원가입 기능 Controller
 */
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
        if(auth.getPrincipal()=="anonymousUser")
            return "로그인 해주세요.";
        new SecurityContextLogoutHandler().logout(request, response, auth);
        return "로그아웃 되었습니다.";
    }

    @ApiOperation(value = "회원 가입",
            notes = "이름 : 한글, 영문 대소문자만 허용, " +
                    "별명 : 영어 소문자만 허용, " +
                    "비밀번호 : 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함, " +
                    "전화번호 : 숫자, " +
                    "이메일 : 이메일 형식")
    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody @Valid MemberSignupRequestDto request) {
        return authService.signup(request) != null ? request.getEmail() + "님 회원가입 되었습니다." : "이미 존재하는 이메일 입니다." ;
    }
}
