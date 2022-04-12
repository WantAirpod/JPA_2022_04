package com.cjy9249.orderShop.service.member;

import com.cjy9249.orderShop.domain.entity.Member;
import com.cjy9249.orderShop.domain.repository.member.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;


    @Test
    @DisplayName("이메일로 member 조회 확인")
    void findMember(){
        String email = "cjy9249@naver.com";
        Member member = memberService.getMemberInfo(email);
        Assertions.assertEquals(member.getEmail(),"cjy9249@naver.com");
    }

}