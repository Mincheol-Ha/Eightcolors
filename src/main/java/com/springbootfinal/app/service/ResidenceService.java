package com.springbootfinal.app.service;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.mapper.ResidenceMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ResidenceService {

    private final ResidenceMapper residenceMapper;

    public ResidenceService(ResidenceMapper residenceMapper) {
        this.residenceMapper = residenceMapper;
    }

    public List<ResidenceDto> getAllResidences() {
        return residenceMapper.getAllResidences();
    }

    public void addResidenceWithPhoto(ResidenceDto residence, MultipartFile photo) {
        residenceMapper.insertResidence(residence);
        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                Path path = Paths.get("uploads/" + photo.getOriginalFilename());
                Files.write(path, bytes);
                residenceMapper.insertPhoto(residence.getResidNo(), path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}