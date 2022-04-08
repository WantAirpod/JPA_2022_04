package com.cjy9249.orderShop.service.security;

import com.cjy9249.orderShop.domain.dto.JwtRequestDto;
import com.cjy9249.orderShop.domain.dto.MemberSignupRequestDto;
import com.cjy9249.orderShop.domain.entity.Member;
import com.cjy9249.orderShop.domain.repository.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public String login(JwtRequestDto request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return principal.getUsername();
    }


    public String signup(MemberSignupRequestDto request) {
        boolean existMember = memberRepository.existsById(request.getEmail());

        // 이미 회원이 존재하는 경우
        if (existMember) return null;
        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);
        memberRepository.save(member);
        return member.getEmail();
    }

}
