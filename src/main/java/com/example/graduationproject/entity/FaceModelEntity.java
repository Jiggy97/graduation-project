package com.example.graduationproject.entity;

import com.example.graduationproject.dto.FaceModelDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "face_model_table")
public class FaceModelEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "faceModelEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FaceModelFileEntity> faceModelFileEntityList = new ArrayList<>();

    public static FaceModelEntity toSaveEntity(FaceModelDTO faceModelDTO) {
        FaceModelEntity faceModelEntity = new FaceModelEntity();
        faceModelEntity.setName(faceModelDTO.getName());
        faceModelEntity.setFileAttached(1);  // file 없음

        return faceModelEntity;
    }
}
