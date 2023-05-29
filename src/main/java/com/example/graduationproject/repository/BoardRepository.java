package com.example.graduationproject.repository;

import com.example.graduationproject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying  // delete 나 update 를 할 때 @Query 와 함께 사용하는 어노테이션
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id=:id")
    // 만약 Query 에 , nativeQuery = true 값이 주어지면 실제 데이터 베이스에서 사용하는 쿼리문처럼 작성 가능
    // 지금은 실제 테이블이나 쿼리문이 아니라 Entity 객체를 사용해서 쿼리문 작성
    void updateHits(@Param("id") Long id);
    // 파라미터의 id와 쿼리문의 :id가 매치된다.
}
