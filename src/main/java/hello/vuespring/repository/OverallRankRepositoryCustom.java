package hello.vuespring.repository;

import hello.vuespring.api.dto.MainPageDto;
import hello.vuespring.api.dto.MajorRankDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OverallRankRepositoryCustom {

    List<MajorRankDto> findRankWithUniversityId(@Param("id") Long id);
    List<MajorRankDto> findRankWithUniversityIdAndYear(@Param("id") Long id, @Param("year") Integer year);
}
