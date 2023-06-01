package com.example.graduationproject.service;

import com.example.graduationproject.dto.FaceDTO;
import com.example.graduationproject.entity.FaceEntity;
import com.example.graduationproject.repository.FaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaceService {
    private final FaceRepository faceRepository;
    public void save(FaceDTO faceDTO) {
        FaceEntity boardEntity = FaceEntity.toSaveEntity(faceDTO);
        faceRepository.save(boardEntity);
    }
}
