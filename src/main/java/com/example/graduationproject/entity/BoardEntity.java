package com.example.graduationproject.entity;

import com.example.graduationproject.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column // default : 크기 255, null 가능  | (unique = true) 속성도 활용가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column // 조회수
    private int boardHits;

    // 이렇게 Entity 파일에서 객체를 변환시키는 코드를 추가해도 되지만 이것은 방법에 따라 다름,
    // Service Class 에 코드하는 사람도 있고 등등
    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }
}
