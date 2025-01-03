package com.springbootfinal.app.controller;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.domain.ResidenceType;
import com.springbootfinal.app.service.ResidenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ResidenceController {

    private final ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping("/residences")
    public String listResidences(Model model) {
        List<ResidenceDto> residences = residenceService.getAllResidences();
        model.addAttribute("residences", residences);
        return "residences";
    }

    @GetMapping("/residences/new")
    public String showAddResidenceForm(Model model) {
        model.addAttribute("residence", new ResidenceDto());
        model.addAttribute("residenceTypes", ResidenceType.values());
        return "addresidences";
    }

    @PostMapping("/residences")
    public String addResidence(@ModelAttribute ResidenceDto residence, @RequestParam("photo") MultipartFile photo) {
        residenceService.addResidenceWithPhoto(residence, photo);
        return "redirect:/residences";
    }
}