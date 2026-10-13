package com.Human.mini_Prj.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class CommentWriteReqDto {
    private String content; // 내용
    private Long boardId;   // 댓글을 작성할 게시글의 ID
    private String email; // 댓글 작성자를 찾기 위한 회원 이메일

}
