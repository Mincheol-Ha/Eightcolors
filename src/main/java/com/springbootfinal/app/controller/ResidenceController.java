package com.springbootfinal.app.controller;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.service.ResidenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ResidenceController {
    @Autowired
    private ResidenceService residenceService;

    @GetMapping("/resid")
    public String getResidence(Model model) {
        model.addAttribute("resList", residenceService.getResidenceList());
        return "/residences/resid";
    }

    // 게시글 리스트를 요청하는 메서드
    @GetMapping("/residenceList")
    public String getResidenceList(Model model) {
        model.addAttribute("resList", residenceService.getResidenceList());
        return "/residences/residenceList";
    }

    // no에 해당하는 게시글 상세보기 요청을 처리하는 메서드
    @GetMapping("/residenceDetail")
    public String getResidenceDetail(@RequestParam("residNo") int residNo, Model model) {
        model.addAttribute("residence", residenceService.getResidenceByNo(residNo));
        return "/residences/residenceDetail";
    }

    @GetMapping("/residenceAdd")
    public String showAddForm(Model model) {
        model.addAttribute("residence", new ResidenceDto());
        return "/residences/residenceAdd";
    }

    @PostMapping("/residenceAdd")
    public String addResidence(ResidenceDto residenceDto, MultipartFile photo) throws IOException {
        // 업로드 디렉토리 경로 설정
        String uploadDir = "uploads/";
        File uploadDirectory = new File(uploadDir);

        // 디렉토리가 없으면 생성
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();  // 디렉토리 생성
        }

        // 사진 파일 처리
        if (photo != null && !photo.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            File saveFile = new File(uploadDir, fileName);
            photo.transferTo(saveFile); // 파일 저장
            residenceDto.setPhotoUrl("/" + uploadDir + fileName);
        }

        // 데이터 저장
        residenceService.addResidence(residenceDto);
        return "redirect:/residenceList";
    }





}