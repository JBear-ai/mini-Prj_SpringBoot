package com.Human.mini_Prj.Service;

import com.Human.mini_Prj.Dto.CommentResDto;
import com.Human.mini_Prj.Dto.CommentWriteReqDto;
import com.Human.mini_Prj.Entity.Board;
import com.Human.mini_Prj.Entity.Comment;
import com.Human.mini_Prj.Entity.Member;
import com.Human.mini_Prj.Exception.CustomException;
import com.Human.mini_Prj.Repository.BoardRepository;
import com.Human.mini_Prj.Repository.CommentRepository;
import com.Human.mini_Prj.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Slf4j
@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional

public class CommentService {
    // 왜 세개나 필요할까?
    // 각각 역할이 다름
    private final BoardRepository boardRepository; // 댓글이 작성될 게시글 확인
    private final MemberRepository memberRepository; // 댓글을 작성하는 회원 확인
    private final CommentRepository commentRepository; // 댓글 저장·조회·수정·삭제

    // CommentWriteReqDto  댓글 작성
    // CommentResDto 댓글 조회 결과 반환 및 수정 요청에 재사용
    // 삭제 DTO 없이 CommentId 사용

    // 특정 게시글의 댓글 목록 조회
    // [설계 의도] 댓글이 없어도 정상 조회로 판단하여 빈 리스트([]) 반환
    // 별도의 CustomException 없이 Controller에서 200 OK 처리
    public List<CommentResDto> getComment(Long boardId){
        List<Comment> commentList = commentRepository.findByBoard_IdOrderByRegDateDesc(boardId);
        List<CommentResDto> commentResDtos = new ArrayList<>();

        for(Comment comment: commentList){
            commentResDtos.add(convertEntityToDto(comment));
        }
        return commentResDtos;

    }

    // 댓글 작성
    public boolean writeComment(CommentWriteReqDto commentWriteReqDto){
        try {
            // 1. 회원 조회
            Optional<Member> member = memberRepository.findByEmail(commentWriteReqDto.getEmail());
            // 2. 게시글 조회
            Optional<Board> board = boardRepository.findById(commentWriteReqDto.getBoardId());
            // 3. Optional에서 실제 객체를 꺼내기
            Member findMember = member.orElseThrow(() -> new CustomException(
                    "MEMBER_NOT_FOUND",
                    "회원이 존재하지 않습니다,"
            ));

            Board findBoard = board.orElseThrow(() -> new CustomException(
                    "BOARD_NOT_FOUND",
                    "게시글이 존재하지 않습니다,"
            ));

            // 4. 새로운 댓글 객체 생성
            Comment comment = new Comment();
            // 5. 댓글 정보 설정
            comment.setContent(commentWriteReqDto.getContent());
            comment.setMember(findMember);
            comment.setBoard(findBoard);
            // 6. DB에 댓글 저장
            commentRepository.save(comment);
            return true;
        } catch (Exception e){
            log.error("댓글 작성 시 오류 발생: {}",e.getMessage());
            throw  e;
        }
    }

    // 댓글 수정
    public boolean modifyComment(CommentResDto commentResDto){
        try {
            // 댓글 ID로 조회
            Optional<Comment> comment = commentRepository.findById(commentResDto.getCommentId());

            // Optional에서 실제 Comment 객체 꺼내기
            Comment findComment = comment.orElseThrow(() -> new CustomException(
                    "COMMENT_NOT_FOUND",
                    "댓글이 존재하지 않습니다."));

            // 기존 댓글 내용 수정
            findComment.setContent(commentResDto.getContent());
//            commentRepository.save(findComment);
//            @Transactional 적용으로 조회한 엔티티의 변경 사항이
//            JPA 변경 감지(Dirty Checking)를 통해 DB에 반영되므로 save() 생략
            return true;
        }catch (Exception e){
            log.error("댓글 수정 실패: {}", e.getMessage());
            throw e;
            // 공통 예외 처리 구조를 적용해볼거면 try-catch를 제거하는 방향 고려
        }
    }

    // 댓글 삭제
    public boolean deleteComment(Long id){
        try {
            Comment comment = commentRepository.findById(id)
                    .orElseThrow(() -> new CustomException(
                            "COMMENT_NOT_FOUND",
                            "댓글이 존재하지 않습니다."));
            commentRepository.delete(comment);
            return true;

        } catch (Exception e) {
            log.error("댓글 삭제 실패: {}", e.getMessage());
            throw e;
        }
    }

    // Entity -> Dto
    private CommentResDto convertEntityToDto(Comment comment){
        CommentResDto commentResDto = new CommentResDto();

        commentResDto.setCommentId(comment.getCommentId());
        commentResDto.setContent(comment.getContent());
        commentResDto.setRegDate(comment.getRegDate());

        commentResDto.setEmail(comment.getMember().getEmail());
        commentResDto.setName(comment.getMember().getName());

        return commentResDto;
    }
}
