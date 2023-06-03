package com.example.graduationproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "face_model_file_table")
public class FaceModelFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private FaceModelEntity faceModelEntity;

    public static FaceModelFileEntity toFaceModelFileEntity(FaceModelEntity faceModelEntity, String originalFileName, String storedFileName) {
        FaceModelFileEntity faceModelFileEntity = new FaceModelFileEntity();
        faceModelFileEntity.setOriginalFileName(originalFileName);
        faceModelFileEntity.setStoredFileName(storedFileName);
        faceModelFileEntity.setFaceModelEntity(faceModelEntity);
        return faceModelFileEntity;
    }
}
