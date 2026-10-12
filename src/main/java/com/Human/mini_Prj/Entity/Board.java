package com.Human.mini_Prj.Entity;

import com.google.errorprone.annotations.InlineMeValidationDisabled;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString


// 게시글 작성

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board id")
    private Long id; // PK는 자동증가

    @Lob
    @Column(length = 10000)
    private String content; // 게시글 내용 10000자

    @Column(length = 100, nullable = false)
    private String title; // 게시글 제목


    private LocalDateTime regTime; // 게시글 작성일자
    @PrePersist
    public void prePersist(){
        this.regTime = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계, 지연 전략
    @JoinColumn(name = "member id") // 외래키
    private Member member; // 작성자 객체를 가져옴


}
