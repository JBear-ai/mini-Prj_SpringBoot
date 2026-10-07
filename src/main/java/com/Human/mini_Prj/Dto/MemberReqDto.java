package com.Human.mini_Prj.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 회원 정보 수정

public class MemberReqDto {
    private String pwd;
    private String name;
    private String image;
    private String email;

}
