package com.Human.mini_Prj.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// 회원정보 요청
public class MemberResDto {
    private String email;
    private String name;
    private String pwd;
    private String image;
    private LocalDateTime regDate;
}
