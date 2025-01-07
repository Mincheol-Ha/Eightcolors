package com.springbootfinal.app.controller;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.service.ResidenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ResidenceController {
    @Autowired
    private ResidenceService residenceService;
    // 업로드한 사진을 저장할 폴더 위치를 상수로 선언
    private static final String UPLOAD_DIR = "uploads/";


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


    /*@PostMapping("/residenceAdd")
    // 여러개의 사진 파일을 업로드할 수 있도록 MultipartFile[]로 수정
    public String addResidence(@ModelAttribute ResidenceDto residenceDto, @RequestParam("photo") MultipartFile[] photos) {
        // 업로드한 사진 파일을 저장할 폴더가 없으면 생성
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 업로드한 사진 파일을 하나씩 처리
        for (MultipartFile photo : photos) {
            // 업로드한 사진 파일이 없으면 다음 사진 파일로 넘어감
            if (photo.isEmpty()) {
                continue;
            }

            // 업로드한 사진 파일의 이름을 UUID로 변경
            String uuid = UUID.randomUUID().toString();
            String originalFileName = photo.getOriginalFilename();
            String saveFileName = uuid + "_" + originalFileName;

            // 업로드한 사진 파일을 저장할 경로를 생성
            Path savePath = Paths.get(UPLOAD_DIR + saveFileName);

            // 업로드한 사진 파일을 저장
            try {
                Files.copy(photo.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 업로드한 사진 파일의 이름을 DTO에 저장
            residenceDto.setPhotoUrl(saveFileName);
        }

        // 게시글을 추가
        residenceService.addResidence(residenceDto);
        return "redirect:/residenceList";
    }*/

    //업로드한 사진이 데이터베이스 property_photos에 저장
/*    @PostMapping("/residenceAdd")
    public String addResidence(@ModelAttribute ResidenceDto residenceDto, @RequestParam("photo") MultipartFile photo) {
        // Create the upload directory if it doesn't exist
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }*/

    @PostMapping("/residenceAdd")
    public String addResidence(@ModelAttribute ResidenceDto residenceDto, @RequestParam("photos") MultipartFile[] photos) {
        // Create the upload directory if it doesn't exist
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<String> photoUrls = new ArrayList<>();
        List<String> thumbnailUrls = new ArrayList<>();

        for (MultipartFile photo : photos) {
            if (!photo.isEmpty()) {
                // Generate a unique filename
                String uuid = UUID.randomUUID().toString();
                String originalFileName = photo.getOriginalFilename();
                String saveFileName = uuid + "_" + originalFileName;

                // Define the save path for the original photo
                Path savePath = Paths.get(UPLOAD_DIR + saveFileName);

                try {
                    // Save the original photo
                    Files.copy(photo.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

                    // Create a thumbnail
                    String thumbnailFileName = uuid + "_thumbnail_" + originalFileName;
                    Path thumbnailPath = Paths.get(UPLOAD_DIR + thumbnailFileName);
                    Thumbnails.of(savePath.toFile())
                            .size(150, 150) // Set the desired thumbnail size
                            .toFile(thumbnailPath.toFile());

                    // Add the filenames to the lists
                    photoUrls.add(saveFileName);
                    thumbnailUrls.add(thumbnailFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Save the photo and thumbnail URLs to the DTO
        residenceDto.setPhotoUrls(photoUrls);
        residenceDto.setThumbnailUrls(thumbnailUrls);

        // Add the residence
        residenceService.addResidence(residenceDto);
        return "redirect:/residenceList";
    }






}