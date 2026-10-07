package com.Human.mini_Prj.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 회원가입 요청
@Getter
@Setter
@NoArgsConstructor

public class SignUpReqDto {
    private String email;
    private String pwd;
    private String name;

}
