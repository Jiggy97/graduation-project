package com.example.graduationproject.service;

import com.example.graduationproject.dto.BoardDTO;
import com.example.graduationproject.entity.BoardEntity;
import com.example.graduationproject.entity.BoardFileEntity;
import com.example.graduationproject.repository.BoardFileRepository;
import com.example.graduationproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service 파일에서 주로 하는 작업
// DTO -> Entity  (Entity Class 에서 다룸)
// Entity -> DTO  (DTO Class 에서 다룸)
// repository 파일에 들어가는 객체는 Entity 객체로 들어가기 때문에
// 즉, DTO 객체는 service 파일 까지만

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().isEmpty()) {
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else {
             /*
                 1. DTO 에 담긴 파일을 꺼냄
                 2. 파일의 이름을 가져옴
                 3. 서버 저장용 이름을 만듬
                 // 내사진.jpg -> 1359135_내사진.jpg
                 4. 저장 경로 설정
                 5. 해당 경로에 파일 저장
                 6. board_table 에 해당 데이터 save
                 7. board_file_table 에 해당 데이터 save 처리
             */
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long savedId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(savedId).get();
            for (MultipartFile boardFile: boardDTO.getBoardFile()) {
//                MultipartFile boardFile = boardDTO.getBoardFile();  // 1. 반복문으로 필요 없어짐
                String originalFileName = boardFile.getOriginalFilename();  // 2.
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;    // 3.
                // 1997년 어쩌구부터 지금까지의 시간_파일명 겹치지 않게 하기 위해서

                String savePath = "/Users/jiggy-ahn/Desktop/springboot_img/" + storedFileName;    // 4.
                boardFile.transferTo(new File(savePath));   // 5.
                // 예외가 발생할 수 있기 때문에 예외처리(Controller 에서도)

                BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFileName, storedFileName);
                boardFileRepository.save(boardFileEntity);
            }
        }
    }

    // findById 와 동일한 이유에서 Transaction 어노테이션을 붙여줌
    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional  // 데이터의 일관성을 유지하기 위해 영속성 컨테스트를 처리하는거라는데 내가아는 그 트랜잭션이 맞고 더 자세히 알아보자!
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    // 부모 Entity 에서 자식 Entity 로 접근할 때 Transaction 을 붙여주어야 함
    // findById 에서 BoardDTO 를 호출했는데 해당 객체 내부에서 boardEntity 가 boardFileEntity 로 접근하기 때문
    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity =  boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        // 1을 빼주는 이유는 페이지 위치 값이 0 부터 시작하기 때문
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3;
        // 1 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // 페이지 위치에 있는 값은 0 부터 시작
        Page<BoardEntity> boardEntities =
        boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        // properties 에 들어가는 값은 entity 에 있는 기준

        // 페이지 객체로 생성하였기 때문에 아래의 메소드들을 통해 정보를 얻을 수 있다. 리스트 객체는 못함(페이징 처리만 할 수 있음)
        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createTime
        // 괄호 내부 board 는 Entity 객체 이고 map 함수의 역할로 DTO 객체로 바꿔주는 유용한 메서드이다.
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreateTime()));
        return boardDTOS;
    }
}
