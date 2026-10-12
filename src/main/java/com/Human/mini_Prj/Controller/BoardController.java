package com.Human.mini_Prj.Controller;

import com.Human.mini_Prj.Dto.BoardResDto;
import com.Human.mini_Prj.Dto.BoardWriteReqDto;
import com.Human.mini_Prj.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;

    // 게시글 전체 조회
    @GetMapping("/list")
    public List<BoardResDto>getBoardList(){
        return boardService.getBoardList();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public BoardResDto getBoard(@PathVariable long id){
        return boardService.getBoard(id);
    }

    // 게시글 작성
    @PostMapping("/write")
    public boolean writeBoard(@RequestBody BoardWriteReqDto boardWriteReqDto){
        return boardService.writeBoard(boardWriteReqDto);
    }


    // 게시글 수정
    @PutMapping("/modify")
    public boolean modifyBoard(@RequestBody BoardResDto boardResDto){
        return boardService.modifyBoard(boardResDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public boolean deleteBoard(@PathVariable long id){
        return boardService.deleteBoard(id);
    }

}
