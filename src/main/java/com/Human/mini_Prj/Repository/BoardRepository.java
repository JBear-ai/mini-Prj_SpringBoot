package com.Human.mini_Prj.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Human.mini_Prj.Entity.Board;
import com.Human.mini_Prj.Entity.Member;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByRegTimeDesc();

    List<Board> findByMember(Member member);

}
