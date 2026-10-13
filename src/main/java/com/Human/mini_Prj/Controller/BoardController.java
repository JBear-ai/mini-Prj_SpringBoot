package com.Human.mini_Prj.Controller;

import com.Human.mini_Prj.Dto.BoardResDto;
import com.Human.mini_Prj.Dto.BoardWriteReqDto;
import com.Human.mini_Prj.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;

    // 게시글 전체 조회
//    @GetMapping("/list")
//    public List<BoardResDto> getBoardList(){
//        return boardService.getBoardList();
//    }

    // 객체를 응답으로 반환(게시글 전체조회)
    @GetMapping("/list")
    public ResponseEntity<List<BoardResDto>> getBoardList(){
        List<BoardResDto> list = boardService.getBoardList();
        return ResponseEntity.ok(list);
    }


    // 게시글 상세 조회
//    @GetMapping("/{id}")
//    public BoardResDto getBoard(@PathVariable long id){
//        return boardService.getBoard(id);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<BoardResDto> getBoard(@PathVariable Long id){
        BoardResDto board = boardService.getBoard(id);
        return ResponseEntity.ok(board);
    }

    // 게시글 작성
//    @PostMapping("/write")
//    public boolean writeBoard(@RequestBody BoardWriteReqDto boardWriteReqDto){
//        return boardService.writeBoard(boardWriteReqDto);
//    }
    @PostMapping("/write")
    public ResponseEntity<Boolean> writeBoard(@RequestBody BoardWriteReqDto boardWriteReqDto){
        boolean result = boardService.writeBoard(boardWriteReqDto);

        if (result) {
            return ResponseEntity.status(201).body(true);
        }
        return ResponseEntity.status(400).body(false);
    }


    // 게시글 수정
//    @PutMapping("/modify")
//    public boolean modifyBoard(@RequestBody BoardResDto boardResDto){
//        return boardService.modifyBoard(boardResDto);
//    }

    @PutMapping("/modify")
    public ResponseEntity<Boolean> modifyBoard(@RequestBody BoardResDto boardResDto){
        boolean putResult = boardService.modifyBoard(boardResDto);

        if (putResult){
            return ResponseEntity.status(200).body(true);
        }
        return ResponseEntity.status(404).body(false);
    }


    // 게시글 삭제
//    @DeleteMapping("/{id}")
//    public boolean deleteBoard(@PathVariable long id){
//        return boardService.deleteBoard(id);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable long id){
       boolean delResult = boardService.deleteBoard(id);

       if(delResult){
           return ResponseEntity.ok(true);
//           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.status(404).body(false);
//       return ResponseEntity.notFound().build();
    }

}
