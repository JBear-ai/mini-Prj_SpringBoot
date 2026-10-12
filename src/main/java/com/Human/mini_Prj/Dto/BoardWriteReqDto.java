package com.Human.mini_Prj.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


// 게시글 작성
public class BoardWriteReqDto {
    private String title; // 제목
    private String content; // 내용
    private String email; // 작성자가 누구인지 찾기 위한 방법 중 하나

    // 작성자 토근을 발행하는 방법이 제일 좋다

}
