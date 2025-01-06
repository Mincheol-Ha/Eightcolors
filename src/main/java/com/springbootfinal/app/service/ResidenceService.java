package com.springbootfinal.app.service;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.mapper.ResidenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ResidenceService {
    @Autowired
    private ResidenceMapper residenceMapper;

    // 전체 게시글을 읽어와 반환
    public List<ResidenceDto> getResidenceList() {
        return residenceMapper.selectResidenceList();
    }

    // no에 해당하는 게시글을 읽어와 반환
    public ResidenceDto getResidenceByNo(int no) {
        log.info("ResidenceService.getResidenceByNo - no: " + no);
        return residenceMapper.selectResidenceByNo(no);

    }
    // 게시글을 DB 테이블에 추가하는 메서드
    public void addResidence(ResidenceDto residenceDto) {
        residenceMapper.insertResidence(residenceDto);
    }

    private String savePhoto(MultipartFile photo) {
        String fileName = photo.getOriginalFilename();
        Path filePath = Paths.get("uploads", fileName);
        try {
            Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath.toString();
    }


}