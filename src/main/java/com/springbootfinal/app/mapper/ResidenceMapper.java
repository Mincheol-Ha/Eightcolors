package com.springbootfinal.app.mapper;

import com.springbootfinal.app.domain.ResidenceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResidenceMapper {

    // XML Mapper에 정의된 메서드를 호출하는 인터페이스 선언
    List<ResidenceDto> getAllResidences();

    ResidenceDto getResidenceById(Long residNo);

    void insertResidence(ResidenceDto residence);

    void updateResidence(ResidenceDto residence);

    void deleteResidence(Long residNo);

    void insertPhoto(Long residNo, String string);
}
