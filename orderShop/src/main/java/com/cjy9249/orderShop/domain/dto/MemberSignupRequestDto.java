package com.cjy9249.orderShop.domain.dto;


import com.cjy9249.orderShop.domain.model.Sex;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * created by cjy9249
 * 정규 표현식을 사용한 Dto
 */
@Getter
@Setter
@Builder
public class MemberSignupRequestDto implements Serializable {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Size(max = 100)
    @ApiModelProperty(example = "cjy9249@naver.com")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{10,20}$" , message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 비밀번호여야 합니다.")
    @Size(min = 10 , max = 20 , message = "10자 이상 입력 하시오.")
    @ApiModelProperty(example = "cjy9249@CJY9249")
    private String password;

    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "한글, 영문 대소문자만 허용합니다.")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(max = 20 , message = "20 글자 이하로 입력하세요.")
    @ApiModelProperty(example = "cjy")
    private String name;

    @Pattern(regexp="^[a-z]*$", message = "영문 소문자만 허용합니다.")
    @Size(max = 30 , message = "30 글자 이하로 입력하세요.")
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @ApiModelProperty(example = "cjy")
    private String nickname;

    @Pattern(regexp = "^[0-9]+$", message = "숫자만 허용합니다.")
    @Size(max = 20, message = "20 자리 숫자 이하로 입력하세요.")
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @ApiModelProperty(example = "010112119")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Sex sex;
}
