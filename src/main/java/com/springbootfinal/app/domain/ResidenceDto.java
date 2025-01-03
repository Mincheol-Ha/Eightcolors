package com.springbootfinal.app.domain;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResidenceDto {
    private Long residNo;
    private String residName;
    private String residDescription;
    private String residAddress;
    private ResidenceType residType;
    private Date checkinDate;
    private Date checkoutDate;
    private BigDecimal totalPrice;
    private Integer discountRate;
    private BigDecimal discountedPrice;
    private BigDecimal rating;
    private Timestamp residDate;

    public void setResidType(String residType) {
        this.residType = ResidenceType.fromString(residType);
    }
}