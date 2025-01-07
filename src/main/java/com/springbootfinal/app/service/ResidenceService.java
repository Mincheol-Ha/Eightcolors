package com.springbootfinal.app.service;

import com.springbootfinal.app.domain.ResidenceDto;
import com.springbootfinal.app.mapper.ResidenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidenceService {
    // ResidenceMapper 인터페이스의 인스턴스를 주입받음
    private final ResidenceMapper residenceMapper;


    // DB 테이블에 새로운 거주지를 추가하는 메서드
    public void insertResidence(ResidenceDto residenceDto) {
        residenceMapper.insertResidence(residenceDto);
    }

    // DB 테이블에 거주지의 사진을 추가하는 메서드
    public void insertPropertyPhotos(ResidenceDto residenceDto) {
        residenceMapper.insertPropertyPhotos(residenceDto);
    }

    // DB 테이블에서 주어진 번호에 해당하는 거주지를 조회하는 메서드
    public ResidenceDto selectResidenceByNo(int residNo) {
        return residenceMapper.selectResidenceByNo(residNo);
    }

    // DB 테이블에서 모든 거주지 목록을 조회하는 메서드
    public List<ResidenceDto> selectResidenceList() {
        return residenceMapper.selectResidenceList();
    }

    // addResidence 메서드를 호출하는 메서드
    public void addResidence(ResidenceDto residenceDto) {
        residenceMapper.insertResidence(residenceDto);
        residenceMapper.insertPropertyPhotos(residenceDto);
    }

    public ResidenceDto findById(Long residNo) {
        // Mock 데이터 예시 - 실제 구현에서는 DB 호출 로직 작성
        ResidenceDto residence = new ResidenceDto();
        residence.setResidNo(residNo);
        residence.setResidName("Example Residence");
        residence.setResidDescription("This is an example description.");
        residence.setResidAddress("123 Example Street");
        residence.setResidType("Apartment");
        residence.setCheckinDate(LocalDate.now());
        residence.setCheckoutDate(LocalDate.now().plusDays(2));
        residence.setTotalPrice(200);
        residence.setDiscountRate(10);
        residence.setDiscountedPrice(180);
        residence.setRating(4);
        residence.setThumbnailUrls("example-thumbnail.jpg");
        residence.setPhotoUrl01("photo1.jpg");
        residence.setPhotoUrl02("photo2.jpg");
        // 나머지 photoUrl 속성 초기화
        return residence;
    }
}