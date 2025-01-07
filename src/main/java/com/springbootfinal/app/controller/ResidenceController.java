package com.springbootfinal.app.controller;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.service.ResidenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ResidenceController {
    private final ResidenceService residenceService;
    private static final String UPLOAD_DIR = "resources/static/uploads/";

    @GetMapping("/resid")
    public String getResidence(Model model) {
        model.addAttribute("resList", residenceService.selectResidenceList());
        return "/residences/resid";
    }

    @GetMapping("/residenceList")
    public String getResidenceList(Model model) {
        model.addAttribute("resList", residenceService.selectResidenceList());
        return "/residences/residenceList";
    }

    //게시글 상세보기 요청을 처리하는 메서드
    @GetMapping("/residenceDetail")
    public String getResidenceDetail(@RequestParam(name = "residNo") Long residNo, Model model) {
        // ResidenceDto 가져오기
        ResidenceDto residence = residenceService.findById(residNo);

        // photoUrls 리스트 생성 및 residence에 설정
        List<String> photoUrls = Arrays.asList(
                        residence.getPhotoUrl01(),
                        residence.getPhotoUrl02(),
                        residence.getPhotoUrl03(),
                        residence.getPhotoUrl04(),
                        residence.getPhotoUrl05(),
                        residence.getPhotoUrl06(),
                        residence.getPhotoUrl07(),
                        residence.getPhotoUrl08(),
                        residence.getPhotoUrl09(),
                        residence.getPhotoUrl10()
                ).stream()
                .filter(Objects::nonNull) // null 값 필터링
                .collect(Collectors.toList());

        residence.setPhotoUrls(photoUrls); // ResidenceDto에 설정

        // 모델에 ResidenceDto 추가
        model.addAttribute("residence", residence);

        return "residences/residenceDetail";
    }



    @GetMapping("/residenceAdd")
    public String showAddForm(Model model) {
        model.addAttribute("residence", new ResidenceDto());
        return "/residences/residenceAdd";
    }

    @PostMapping("/residenceAdd")
    public String addResidence(@ModelAttribute ResidenceDto residenceDto, @RequestParam("photos") MultipartFile[] photos) {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<String> photoUrls = new ArrayList<>();
        String thumbnailUrl = null;

        for (int i = 0; i < photos.length; i++) {
            MultipartFile photo = photos[i];
            if (!photo.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                String originalFileName = photo.getOriginalFilename();
                String saveFileName = uuid + "_" + originalFileName;

                Path savePath = Paths.get(UPLOAD_DIR, saveFileName);

                try {
                    Files.copy(photo.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

                    if (i == 0) {
                        String thumbnailFileName = uuid + "_thumbnail_" + originalFileName;
                        Path thumbnailPath = Paths.get(UPLOAD_DIR, thumbnailFileName);
                        Thumbnails.of(savePath.toFile())
                                .size(400, 400)
                                .toFile(thumbnailPath.toFile());
                        thumbnailUrl = thumbnailFileName;
                    }

                    photoUrls.add(saveFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        residenceDto.setThumbnailUrls(thumbnailUrl);
        if (photoUrls.size() > 0) residenceDto.setPhotoUrl01(photoUrls.get(0));
        if (photoUrls.size() > 1) residenceDto.setPhotoUrl02(photoUrls.get(1));
        if (photoUrls.size() > 2) residenceDto.setPhotoUrl03(photoUrls.get(2));
        if (photoUrls.size() > 3) residenceDto.setPhotoUrl04(photoUrls.get(3));
        if (photoUrls.size() > 4) residenceDto.setPhotoUrl05(photoUrls.get(4));
        if (photoUrls.size() > 5) residenceDto.setPhotoUrl06(photoUrls.get(5));
        if (photoUrls.size() > 6) residenceDto.setPhotoUrl07(photoUrls.get(6));
        if (photoUrls.size() > 7) residenceDto.setPhotoUrl08(photoUrls.get(7));
        if (photoUrls.size() > 8) residenceDto.setPhotoUrl09(photoUrls.get(8));
        if (photoUrls.size() > 9) residenceDto.setPhotoUrl10(photoUrls.get(9));

        residenceService.addResidence(residenceDto);
        return "redirect:/residenceList";
    }
}