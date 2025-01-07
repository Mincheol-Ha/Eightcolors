package com.springbootfinal.app.mapper;

import com.springbootfinal.app.domain.ResidenceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResidenceMapper {

   // DB 테이블에서 모든 게시글을 읽어와 반환하는 메서드
    public List<ResidenceDto> selectResidenceList();

    // DB 테이블에서 게시글 번호에 해당하는 게시글을 읽어와 반환하는 메서드
    public ResidenceDto selectResidenceByNo(int residNo);

    // DB 테이블에 게시글을 추가하는 메서드
    public void insertResidence(ResidenceDto residenceDto);

    // DB 테이블에 게시글의 사진을 추가하는 메서드
    public void insertPropertyPhotos(ResidenceDto residenceDto);





}