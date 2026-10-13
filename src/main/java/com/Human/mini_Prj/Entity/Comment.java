package com.Human.mini_Prj.Entity;


import com.google.errorprone.annotations.InlineMeValidationDisabled;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId; // comment_id;  1.댓글 고유 번호

    @ManyToOne(fetch = FetchType.LAZY) // 지연 전략
    @JoinColumn(name = "board_id") // 외래 키  2.어느 게시글에 달린 댓글인가 찾기
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 전략
    @JoinColumn(name = "member_id") // 외래 키  3. 누가 댓글을 작성하였는가?
    private Member member;

    @Column(length = 1000)
    private String content;  // 4. 댓글의 내용

    private LocalDateTime regDate;
    @PrePersist
    public void PrePersist(){ // 5. 댓글 작성시간
        regDate = LocalDateTime.now();

    }

}
