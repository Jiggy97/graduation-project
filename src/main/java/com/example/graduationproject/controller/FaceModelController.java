package com.example.graduationproject.controller;

import com.example.graduationproject.dto.FaceModelDTO;
import com.example.graduationproject.service.FaceModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/face")
public class FaceModelController {
    private final FaceModelService faceModelService;

    @GetMapping("/saveModel")
    public String saveForm() {
        return "saveModel";
    }

    @PostMapping("/saveModel")
    public String saveModel(@ModelAttribute FaceModelDTO faceModelDTO) throws IOException {
        faceModelService.save(faceModelDTO);

        return "success-save-model";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<FaceModelDTO> faceModelDTOList = faceModelService.findAll();
        model.addAttribute("faceModelList", faceModelDTOList);
        return "modelList";
    }
}

