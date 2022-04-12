package com.cjy9249.orderShop.service.security;

import com.cjy9249.orderShop.domain.dto.JwtRequestDto;
import com.cjy9249.orderShop.domain.dto.MemberSignupRequestDto;
import com.cjy9249.orderShop.domain.model.Sex;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("회원가입 및 로그인 테스트")
    public void signUpLoginTest() throws Exception {
        //회원가입
        MemberSignupRequestDto userSignUp = MemberSignupRequestDto.builder()
                .name("cjy")
                .email("cjy9249@naver.com")
                .nickname("cjyq")
                .password("cjy9249@CJY9249")
                .phoneNumber("01922")
                .sex(Sex.female)
                .build()
                ;
        String testSignUpStr = authService.signup(userSignUp);
        Assertions.assertEquals(testSignUpStr,"cjy9249@naver.com");

        //로그인
        JwtRequestDto user = new JwtRequestDto();
        user.setEmail("cjy9249@naver.com");
        user.setPassword("cjy9249@CJY9249");
        String loginTestStr = authService.login(user);
        Assertions.assertEquals(loginTestStr,"cjy");

    }

}