package com.example.graduationproject.controller;


import com.example.graduationproject.dto.BoardDTO;
import com.example.graduationproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;    // 생성자 주입 방식으로 의존성을 주입받음

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    // Get 과 Post 의 경우 주소가 같아도 오류가 발생하지 않는다 그러나 같은 어노테이션의 경우 주소가 같으면 오류가 발생한다.
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.print("boardDTO = " + boardDTO);
        boardService.save(boardDTO);

        return "index";
    }
}
