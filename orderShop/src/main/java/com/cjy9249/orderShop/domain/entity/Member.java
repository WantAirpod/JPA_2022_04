package com.cjy9249.orderShop.domain.entity;

import com.cjy9249.orderShop.domain.dto.MemberSignupRequestDto;
import com.cjy9249.orderShop.domain.model.Role;
import com.cjy9249.orderShop.domain.model.Sex;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "tb_member")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(MemberSignupRequestDto request) {
        email = request.getEmail();
        name = request.getName();
        password = request.getPassword();
        phoneNumber = request.getPhoneNumber();
        sex = request.getSex();
        role = Role.LOGINCHECK; // 회원가입하는 사용자 권한 기본 USER (임시)
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}
