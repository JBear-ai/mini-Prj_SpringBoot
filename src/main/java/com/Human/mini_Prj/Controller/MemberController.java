package com.Human.mini_Prj.Controller;

import com.Human.mini_Prj.Dto.MemberReqDto;
import com.Human.mini_Prj.Dto.MemberResDto;
import com.Human.mini_Prj.Repository.MemberRepository;
import com.Human.mini_Prj.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    // 회원 전체 조회 findAll()
    @GetMapping("/list")
    public List<MemberResDto>findAll(){
        // MemberService 전체 조회를 호출
        return memberService.findAll();
    }

    // 회원 상세 조회 findByEmail()
    @GetMapping("/{email}")
    public MemberResDto findByEmail(@PathVariable String email){
        return memberService.findByEmail(email);
    }

    // 회원 수정 modifyMember()
    @PutMapping("/modify")
    public boolean modifyMember(@RequestBody MemberReqDto memberReqDto){
        return memberService.modifyMember(memberReqDto);
    }

    // 회원 삭제 deleteMember()
    @DeleteMapping("/{email}")
    public boolean deleteMember(@PathVariable String email){
        return memberService.deleteMember(email);
    }



}
