package com.Human.mini_Prj.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 로그인 요청

public class LoginReqDto {
    private String email;
    private String pwd;
}
