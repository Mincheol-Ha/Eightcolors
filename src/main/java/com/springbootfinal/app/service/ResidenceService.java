package com.springbootfinal.app.service;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.mapper.ResidenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ResidenceService {
    @Autowired
    private ResidenceMapper residenceMapper;

    // 전체 게시글을 읽어와 반환
    public List<ResidenceDto> getResidenceList() {
        return residenceMapper.selectResidenceList();
    }

    // no에 해당하는 게시글을 읽어와 반환
    public ResidenceDto getResidenceByNo(int no) {
        log.info("ResidenceService.getResidenceByNo - no: " + no);
        return residenceMapper.selectResidenceByNo(no);

    }
    // 게시글을 DB 테이블에 추가하는 메서드
    public void addResidence(ResidenceDto residenceDto) {
        residenceMapper.insertResidence(residenceDto);

    }



}