package com.example.graduationproject.dto;


import com.example.graduationproject.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

// Data Transfer Object, VO, Bean  <-- 비슷한 목적을 가짐 / 데이터를 주고받을 때 하나의 객체로 정해서 주고받자
// Entity 얘는 좀 다른 목적
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    // 보통 이러한 클래스 객체를 만들면 필드를 주고 필드는 프라이빗으로 접근제한자를 둔다
    // 그러고 이 아래 보통 생성자나 게터세터를 둬서 필드값을 주고받는 형식을 많이 사용한다.

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreateTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdateTime());
        return boardDTO;
    }
}
