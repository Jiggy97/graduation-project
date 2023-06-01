package com.example.graduationproject.controller;

import com.example.graduationproject.dto.FaceDTO;
import com.example.graduationproject.service.FaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/face")
public class FaceController {
    private final FaceService faceService;

    @GetMapping("/saveModel")
    public String saveForm() {
        return "faceSave";
    }

    @PostMapping("/saveModel")
    public String saveModel(@ModelAttribute FaceDTO faceDTO) {
        faceService.save(faceDTO);
        return "face";
    }
}
