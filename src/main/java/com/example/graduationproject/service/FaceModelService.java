package com.example.graduationproject.service;

import com.example.graduationproject.dto.FaceModelDTO;
import com.example.graduationproject.entity.FaceModelEntity;
import com.example.graduationproject.entity.FaceModelFileEntity;
import com.example.graduationproject.repository.FaceModelFileRepository;
import com.example.graduationproject.repository.FaceModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaceModelService {
    private final FaceModelRepository faceModelRepository;
    private final FaceModelFileRepository faceModelFileRepository;
    public void save(FaceModelDTO faceModelDTO) throws IOException {
        FaceModelEntity faceModelEntity = FaceModelEntity.toSaveEntity(faceModelDTO);
        Long saveId = faceModelRepository.save(faceModelEntity).getId();
        FaceModelEntity faceModel = faceModelRepository.findById(saveId).get();
        for (MultipartFile faceModelFile : faceModelDTO.getFaceModelFile()) {
            String originalFileName = faceModelFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

            String savePath = "/Users/jiggy-ahn/Desktop/face_ML_model/" + storedFileName;
            faceModelFile.transferTo(new File(savePath));

            FaceModelFileEntity faceModelFileEntity = FaceModelFileEntity.toFaceModelFileEntity(faceModel, originalFileName, storedFileName);
            faceModelFileRepository.save(faceModelFileEntity);
        }
    }

    public List<FaceModelDTO> findAll() {
        List<FaceModelEntity> faceModelEntityList = faceModelRepository.findAll();
        List<FaceModelDTO> faceModelDTOList = new ArrayList<>();
        for (FaceModelEntity faceModelEntity : faceModelEntityList) {
            faceModelDTOList.add(FaceModelDTO.toFaceModelDTO(faceModelEntity));
        }
        return faceModelDTOList;
    }
}
