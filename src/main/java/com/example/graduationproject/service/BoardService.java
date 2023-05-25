package com.example.graduationproject.service;

import com.example.graduationproject.dto.BoardDTO;
import com.example.graduationproject.entity.BoardEntity;
import com.example.graduationproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Service 파일에서 주로 하는 작업
// DTO -> Entity  (Entity Class 에서 다룸)
// Entity -> DTO  (DTO Class 에서 다룸)
// repository 파일에 들어가는 객체는 Entity 객체로 들어가기 때문에
// 즉, DTO 객체는 service 파일 까지만

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
