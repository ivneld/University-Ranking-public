package hello.vuespring.repository;

import hello.vuespring.api.dto.TempDto;
import hello.vuespring.repository.dao.DuplicateUniversityDao;

import java.util.List;

public interface UniversityRepositoryCustom {

    public List<TempDto> findAllInfo();

    public List<String> findDuplicate();
}
