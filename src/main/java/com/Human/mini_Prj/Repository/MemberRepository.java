package com.Human.mini_Prj.Repository;

import com.Human.mini_Prj.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
// 설계 명세서
// 기본적인 CRUD 는 상속을 통해서 이미 만들어짐 이를 구현하는 구현체는 별도의 hibernate가 SQL문으로 변경 해줌
{
    // 데이터를 DB에서 조회,저장,수정,삭제하기 위한 데이터 접근 기능을 선언 공간

    Optional<Member> findByEmail(String email); // sql : select * from member where email = ?
    boolean existsByEmail(String email); // sql : select count(*) from member where email = ?
    Optional<Member> findByEmailAndPwd(String email, String pwd); // sql : select * from member where email = ? and pwd=?

    // 1. 이름이 정확히 일치하는 회원 목록 조회
    // 회원이 아닌 회원 목록 조회 List로 판별
    List<Member> findByName(String name);

    // 2. 이름에 특정 글자가 포함된 회원 조회
    List<Member> findByNameContaining(String name);

    // 3. 특정 도메인 (예: @gmail.com)으로 끝나는 이메일을 가진 회원 조회
    List<Member> findByEmailEndingWith(String email);

    // 4. 이름 또는 이메일이 일치하는 회원 조회
    List<Member> findByNameOrEmail(String name, String email);

    // 5. 특정 일시 이후에 가입한 회원 조회
    List<Member> findByRegDateAfter(LocalDateTime regDate);

    // 6. 두 일시 사이에 가입한 회원 조회
    // 두 일시 이기에 두개의 값이 필요하다가 판단
    List<Member> findByRegDateBetween(LocalDateTime date1, LocalDateTime date2);

    // 7. 전체 회원을 가입일 최신순으로 조회
    List<Member> findAllByOrderByRegDateDesc();

    // 8. 가장 최근에 가입한 회원 3명만 조회
    List<Member> findTop3ByOrderByRegDateDesc();

    // 9. 대소문자 구분 없이 이름에 키워드가 포함된 회원을 이름 오름차순으로 조회
    List<Member> findByOrderByNameAsc(String name);
    // 10. 이메일로 회원 삭제

}
