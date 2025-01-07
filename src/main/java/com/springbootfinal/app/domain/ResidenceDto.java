package com.springbootfinal.app.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
public class ResidenceDto {
    private Long residNo;
    private String residName;
    private String residDescription;
    private String residAddress;
    private String residType;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private int totalPrice;
    private int discountRate;
    private int discountedPrice;
    private int rating;
    private Timestamp residDate;
    private String thumbnailUrls;
    private String photoUrl01;
    private String photoUrl02;
    private String photoUrl03;
    private String photoUrl04;
    private String photoUrl05;
    private String photoUrl06;
    private String photoUrl07;
    private String photoUrl08;
    private String photoUrl09;
    private String photoUrl10;

    // 추가 필드
    private List<String> photoUrls;
}