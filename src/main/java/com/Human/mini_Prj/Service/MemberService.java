package com.Human.mini_Prj.Service;

import com.Human.mini_Prj.Dto.MemberReqDto;
import com.Human.mini_Prj.Dto.MemberResDto;
import com.Human.mini_Prj.Entity.Member;
import com.Human.mini_Prj.Repository.MemberRepository;
import com.Human.mini_Prj.Exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.CustomLog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j      // log 메세지
@Service    // Spring Container에 Bean 등록
@Transactional // 여러개의 물리적인 작업을 논리형 작업으로 묶음
@RequiredArgsConstructor // 생성자를 통한 의존성 주입을 자동으로
@Getter
@Setter

public class MemberService {
    private final MemberRepository memberRepository; // MemberRepository를 의존성 주입 받음

    // 회원 전체 조회
    // 여러 회원 정보를 반환하므로 List<MemberResDto>를 반환
    public List<MemberResDto> findAll(){
        // DB에서 전체 회원 정보를 Member(Entity) 리스트로 조회
        List<Member> members = memberRepository.findAll();

        // 반환할 MemberResDto들을 담을것
        List<MemberResDto> memberResDtos = new ArrayList<>();

        for (Member member : members){
            memberResDtos.add(convertEntityToDto(member));
        }
        return memberResDtos;
    }

    // 회원 상세 조회
    public MemberResDto findByEmail(String email){

        // 이메일을 기준으로 회원 한 명을 조회
        // 조회 결과가 없을 수도 있으므로 Optional로 반환되며,
        // 회원이 없으면 orElseThrow()로 예외 발생
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(
                "NOT_FOUND",
                "해당 이메일의 사용자를 찾을 수 없습니다."
                )
        );
        // 조회한 Member(Entity)를 MemberResDto로 변환하여 반환
        return convertEntityToDto(member);
    }

    // 회원 수정
    public boolean modifyMember(MemberReqDto memberReqDto)
    {
    try {
        Member member = memberRepository.findByEmail(memberReqDto.getEmail()).orElseThrow(
                () ->new CustomException(
                        "NOT_FOUND",
                        "해당 이메일의 사용자를 찾을 수 없습니다"
                )
        );
        member.setName(memberReqDto.getName());
        member.setImage(memberReqDto.getImage());
        memberRepository.save(member);
        return true;
        }catch (Exception e)
        {
            log.error("회원 정보 수정 실패: {}",e.getMessage());
            return false;
        }

    }


    // 회원 삭제
    public boolean deleteMember (String email){
        try {
            Member member = memberRepository.findByEmail(email).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재 하지 않습니다.")
            );
            memberRepository.delete(member);
            return true;
        } catch (Exception e){
            log.error("회원 삭제 실패: {}", e.getMessage());
            return false;
        }
    }

    // Entity를 DTO로 변환하는 메서드
    private MemberResDto convertEntityToDto(Member member){

        MemberResDto memberResDto = new MemberResDto();

        // Member의 값을 memberResDto에 옮기기
        memberResDto.setEmail(member.getEmail());
        memberResDto.setPwd(member.getPwd());
        memberResDto.setName(member.getName());
        memberResDto.setImage(member.getImage());
        memberResDto.setRegDate(member.getRegDate());

        return memberResDto;
    }

}
