package com.example.graduationproject.controller;

import com.example.graduationproject.dto.FaceModelDTO;
import com.example.graduationproject.service.FaceModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/model")
public class FaceModelController {
    private final FaceModelService faceModelService;

    @GetMapping("/saveModel")
    public String saveForm() {
        return "saveModel";
    }

    @PostMapping("/saveModel")
    public String saveModel(@ModelAttribute FaceModelDTO faceModelDTO) throws IOException {
        faceModelService.save(faceModelDTO);

        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<FaceModelDTO> faceModelDTOList = faceModelService.findAll();
        model.addAttribute("faceModelList", faceModelDTOList);
        return "modelList";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        FaceModelDTO faceModelDTO = faceModelService.findById(id);
        model.addAttribute("model", faceModelDTO);
        return "model-detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        FaceModelDTO updateDTO = faceModelService.findById(id);
        model.addAttribute("updateDTO", updateDTO);
        return "modelUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute FaceModelDTO faceModelDTO, Model model) throws IOException {
        FaceModelDTO faceModel = faceModelService.update(faceModelDTO);
        model.addAttribute("model", faceModel);

        return "model-detail";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        faceModelService.delete(id);

        return "modelList";
    }

}

