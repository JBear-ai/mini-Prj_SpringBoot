package com.Human.mini_Prj.Service;

import com.Human.mini_Prj.Dto.BoardResDto;
import com.Human.mini_Prj.Dto.BoardWriteReqDto;
import com.Human.mini_Prj.Dto.MemberResDto;
import com.Human.mini_Prj.Exception.CustomException;
import com.Human.mini_Prj.Repository.BoardRepository;
import com.Human.mini_Prj.Repository.MemberRepository;
import com.Human.mini_Prj.Entity.Board;
import com.Human.mini_Prj.Entity.Member;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional

public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 전체 조회
    public List<BoardResDto> getBoardList(){
        List<Board> boardList = boardRepository.findAllByOrderByRegTimeDesc();
        List<BoardResDto> boardResDtos = new ArrayList<>();

        for (Board board: boardList){
            boardResDtos.add(convertEntityToDto(board));
        }
        return boardResDtos;
    }

    // 게시글 상세 조회
    public BoardResDto getBoard(Long id){
//        Board board = boardRepository.findById(id);
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()){
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        return convertEntityToDto(board.get());
    }


    // 게시글 작성
    public boolean writeBoard(BoardWriteReqDto boardWriteReqDto){
        try {
            // 작성자 조회
            Optional<Member> member = memberRepository.findByEmail(boardWriteReqDto.getEmail());
            if (member.isEmpty()){
                throw new IllegalArgumentException("해당 회원이 존재하지 않습니다," + boardWriteReqDto.getEmail());
            }
            Board board = new Board();
            board.setTitle(boardWriteReqDto.getTitle());
            board.setContent(boardWriteReqDto.getContent());
            board.setMember(member.get());
            boardRepository.save(board); // INSERT와 동일
            return true;
        }catch (Exception e){
            log.error("게시글 작성 시 오류 발생: {}", e.getMessage());
            return false;
        }

    }


    // 게시글 수정
    public boolean modifyBoard(BoardResDto boardResDto){
        try {
            Optional <Member> member = memberRepository.findByEmail(boardResDto.getEmail()).orElseThrow(
                    () -> new CustomException(
                            "NoT",
                            ""
                    )
            );


        } catch (Exception e)
            log.error("");
            return false;
    }


    // 게시글 삭제



    // Entity -> Dto
    private BoardResDto convertEntityToDto(Board board){
        BoardResDto boardResDto = new BoardResDto();

        // Member의 값을 memberResDto에 옮기기
        boardResDto.setId(board.getId());
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setEmail(board.getMember().getEmail());
        boardResDto.setName(board.getMember().getName());
        boardResDto.setRegDate(board.getRegTime());
        return boardResDto;
    }
}
