package com.Human.mini_Prj.Controller;

import com.Human.mini_Prj.Dto.CommentResDto;
import com.Human.mini_Prj.Dto.CommentWriteReqDto;
import com.Human.mini_Prj.Service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

//    @GetMapping("/list") // 특정 게시글 댓글 목록 조회(기존 방식)
//    public List<CommentResDto> getComment(@RequestParam Long boardId){
//        return commentService.getComment(boardId);
//    }

    @GetMapping("/list") // 특정 게시글 댓글 목록 조회
    public ResponseEntity<List<CommentResDto>> getComment(@RequestParam Long boardId){
        List<CommentResDto> getList = commentService.getComment(boardId);
        return ResponseEntity.ok(getList);
    }


//    @PostMapping("/write") // 댓글 작성(기존 방식)
//    public boolean writeComment(@RequestBody CommentWriteReqDto commentWriteReqDto){
//        return commentService.writeComment(commentWriteReqDto);
//    }

    @PostMapping("/write") // 댓글 작성
    public ResponseEntity <Boolean> writeComment(@RequestBody CommentWriteReqDto commentWriteReqDto){
        boolean postResult = commentService.writeComment(commentWriteReqDto);
        return ResponseEntity.ok(postResult);
    }


//    @PutMapping("/modify") // 댓글 수정 (기존 방식)
//    public boolean modifyComment(@RequestBody CommentResDto commentResDto){
//        return commentService.modifyComment(commentResDto);
//    }

    @PutMapping("/modify") // 댓글 수정
    public ResponseEntity<Boolean> modifyComment(@RequestBody CommentResDto commentResDto) {
        boolean putResult = commentService.modifyComment(commentResDto);

        return ResponseEntity.ok(putResult);
    }



//    @DeleteMapping("/{id}") // 댓글 삭제(기존 방식)
//    public boolean deleteComment(@PathVariable Long id){
//        return commentService.deleteComment(id);
//
//        // ResponseStatusException 방식
//        // HTTP 오류를 예외로 발생하는 역할
////        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글이 존재하지 않습니다.");
//
//    }

    @DeleteMapping("/{id}") // 댓글 삭제
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {

        boolean delResult = commentService.deleteComment(id);

        // [학습 기록] Service에서 false를 반환하지 않으므로 현재 사용하지 않음.
        // 댓글이 존재하지 않으면 Service에서 예외가 발생하여 아래 404 응답까지 도달하지 못함.
        // 추후 예외 처리 방식 학습 시 참고.
        //
        // if (delResult) {
        //     return ResponseEntity.ok(true);
        // }
        // return ResponseEntity.status(404).body(false);

        return ResponseEntity.ok(delResult);
    }
    // 정상 실행 경로 기준으로 판정 PASS
    // 예외 상황에 대한 HTTP 응답처리 미구현 (17:00)
}
