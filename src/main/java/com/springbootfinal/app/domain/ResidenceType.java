package com.springbootfinal.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResidenceType {
    HOTEL("hotel"),
    RESORT("resort"),
    PENSION("pension");

    private final String type;

    ResidenceType(String type) {
        this.type = type;
    }

    public static ResidenceType fromString(String value) {
        for (ResidenceType type : ResidenceType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 값: " + value + ". 유효한 값은 resort, hotel, pension입니다.");
    }

}
