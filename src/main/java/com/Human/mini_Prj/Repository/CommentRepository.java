package com.Human.mini_Prj.Repository;

import com.Human.mini_Prj.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    // 댓글 ID가 아닌 게시글 ID를 기준으로 해당 게시글의 댓글 목록 조회
    List<Comment> findByBoardIdOrderByRegDateDesc(Long boardId);

    List<Comment> findByBoard_IdOrderByRegDateDesc(Long boardId);


//    List<Comment> findByMember_Email(String memberEmail);
/*    이미 멤버리포지토리에 이메일을 찾는게 있어서 의미가 없다.
      메서드	의미
        memberRepository.findByEmail(email)	        / 이메일에 해당하는 회원 한 명 조회
        commentRepository.findByMember_Email(email)	/ 해당 이메일 회원이 작성한 댓글들 조회
        commentRepository.findByBoard_Id(boardId)	/ 해당 게시글에 작성된 댓글들 조회
        세 메서드 모두 조회 기능이지만 조회 대상이 다름

        회원을 찾고 싶다 → MemberRepository
        게시글을 찾고 싶다 → BoardRepository
        댓글을 찾고 싶다 → CommentRepository

        1.Entity,Dto
        2.Repository
        3.Service
        4.Controller
        5.PostMan 검증
        6.예외처리 및 코드개선
 */
}
