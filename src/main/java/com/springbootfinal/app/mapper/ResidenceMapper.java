package com.springbootfinal.app.mapper;


import com.springbootfinal.app.domain.ResidenceDto;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ResidenceMapper {

    // 게시글 리스트를 DB 테이블에서 읽어와 반환하는 메서드
    public List<ResidenceDto> selectResidenceList();

    // DB 테이블에서 no에 해당하는 게시글을 읽어와 반환하는 메서드
    public ResidenceDto selectResidenceByNo(int no);

    // 게시글을 DB 테이블에 추가하는 메서드
    void insertResidence(ResidenceDto residenceDto);

}