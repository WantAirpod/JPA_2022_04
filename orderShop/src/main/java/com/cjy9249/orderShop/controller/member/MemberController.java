package com.cjy9249.orderShop.controller.member;


import com.cjy9249.orderShop.common.Response;
import com.cjy9249.orderShop.domain.entity.Member;
import com.cjy9249.orderShop.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by cjy9249
 * 회원 정보 조회 기능 Controller
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "회원 정보 조회", notes = "이메일로 단일 회원 상세정보 조회 합니다.")
    @GetMapping("info")
    public ResponseEntity<Response<Member>> getUserInfo(
            @RequestParam(value = "email") String email
    ) {
        return ResponseEntity.ok(
                Response.of(
                        memberService.getMemberInfo(email),
                        "불러오기 완료"
                )
        );
    }
}
