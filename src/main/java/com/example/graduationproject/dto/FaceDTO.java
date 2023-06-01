package com.example.graduationproject.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FaceDTO {
    private Long id;
    private String name;
    private byte[] serializedModel;
}
