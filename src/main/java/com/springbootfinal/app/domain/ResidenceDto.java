package com.springbootfinal.app.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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
    private BigDecimal totalPrice;
    private Integer discountRate;
    private BigDecimal discountedPrice;
    private BigDecimal rating;
    private Timestamp residDate;
    private String photoUrl;

    @ToString.Exclude // 사진 데이터도 toString()에서 제외
    private MultipartFile photo;

    private String thumbnailUrl;

    private List<String> photoUrls;
    private List<String> thumbnailUrls;


}