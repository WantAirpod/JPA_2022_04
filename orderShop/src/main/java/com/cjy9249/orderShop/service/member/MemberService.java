package com.cjy9249.orderShop.service.member;

import com.cjy9249.orderShop.common.exception.BaseException;
import com.cjy9249.orderShop.domain.entity.Member;
import com.cjy9249.orderShop.domain.repository.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    /**
     * 회원가입 시, 유효성 체크
     * @param errors
     * @return
     */
    public static Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    /**
     * 단일 회원 상세 정보 조회
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public Member getMemberInfo(String email){
        if(memberRepository.findByemail(email)==null)
            throw new BaseException("존재하지 않는 회원입니다.");
        else
            return memberRepository.findByemail(email);
    }
}
