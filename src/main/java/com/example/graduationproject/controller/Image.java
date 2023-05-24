package com.example.graduationproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class Image {
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }
}
