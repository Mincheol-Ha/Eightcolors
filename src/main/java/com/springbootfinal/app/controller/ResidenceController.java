package com.springbootfinal.app.controller;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.service.ResidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ResidenceController {
    @Autowired
    private ResidenceService residenceService;

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

    // 게시글 쓰기 폼 요청 처리 메서드
    @GetMapping("/residenceAdd")
    public String addResidenceDto() {
        return "/residences/residenceAdd";
    }

    // 게시글을 DB 테이블에 추가하는 메서드
    @PostMapping("/residenceAdd")
    public String addResidence(@ModelAttribute ResidenceDto residenceDto) {
        residenceService.addResidence(residenceDto);
        return "redirect:/residenceList";
    }







}