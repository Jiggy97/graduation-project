package com.example.graduationproject.dto;

import com.example.graduationproject.entity.FaceModelEntity;
import com.example.graduationproject.entity.FaceModelFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FaceModelDTO {
    private Long id;
    private String name;
    private LocalDateTime modelSavedTime;
    private LocalDateTime modelUpdatedTime;

    private List<MultipartFile> faceModelFile;
    private List<String> originalFileName;
    private List<String> storedFileName;

    private int fileAttached;

    public FaceModelDTO(Long id, String name, LocalDateTime modelSavedTime) {
        this.id = id;
        this.name = name;
        this.modelSavedTime = modelSavedTime;
    }

    public static FaceModelDTO toFaceModelDTO(FaceModelEntity faceModelEntity) {
        FaceModelDTO faceModelDTO = new FaceModelDTO();
        faceModelDTO.setId(faceModelEntity.getId());
        faceModelDTO.setName(faceModelEntity.getName());
        faceModelDTO.setModelSavedTime(faceModelEntity.getCreateTime());
        faceModelDTO.setModelUpdatedTime(faceModelEntity.getUpdateTime());

        List<String> originalFileNameList = new ArrayList<>();
        List<String> storedFileNameList = new ArrayList<>();
        faceModelDTO.setFileAttached(faceModelEntity.getFileAttached());    // 1
        for (FaceModelFileEntity faceModelFileEntity : faceModelEntity.getFaceModelFileEntityList()) {
            originalFileNameList.add(faceModelFileEntity.getOriginalFileName());
            storedFileNameList.add(faceModelFileEntity.getStoredFileName());
        }
        faceModelDTO.setOriginalFileName(originalFileNameList);
        faceModelDTO.setStoredFileName(storedFileNameList);

        return faceModelDTO;
    }
}
