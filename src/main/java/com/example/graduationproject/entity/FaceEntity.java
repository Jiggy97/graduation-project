package com.example.graduationproject.entity;

import com.example.graduationproject.dto.FaceDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "face_table")
public class FaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column
    private int fileAttached;

    public static FaceEntity toSaveEntity(FaceDTO faceDTO) {
        FaceEntity faceEntity = new FaceEntity();
        faceEntity.setName(faceDTO.getName());
        faceEntity.setFileAttached(0);  // file 없음

        return faceEntity;
    }
}
