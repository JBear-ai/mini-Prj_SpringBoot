package com.Human.mini_Prj.Repository;

import com.Human.mini_Prj.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
// 설계 명세서
// 기본적인 CRUD 는 상속을 통해서 이미 만들어짐 이를 구현하는 구현체는 별도의 hibernate가 SQL문으로 변경 해줌
{
    Optional<Member> findByEmail(String email); // sql : select * from member where email = ?
    boolean existsByEmail(String email); // sql : select count(*) from member where email = ?
    Optional<Member> findByEmailAndPwd(String email, String pwd); // sql : select * from member where email = ? and pwd=?



}
