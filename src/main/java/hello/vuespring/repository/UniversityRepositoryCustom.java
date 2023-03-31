package hello.vuespring.repository;

import hello.vuespring.api.dto.TempDto;

import java.util.List;

public interface UniversityRepositoryCustom {

    public List<TempDto> findAllInfo();
}
