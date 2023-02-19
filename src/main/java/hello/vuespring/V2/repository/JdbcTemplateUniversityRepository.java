package hello.vuespring.V2.repository;

import hello.vuespring.V2.domain.University;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface JdbcTemplateUniversityRepository {

    University save(University university);

    void update(Long universityId, UniversityUpdateDto updateParam);

    Optional<University> findById(Long id);

    List<University> selectAll();

    Optional<University> findByName(String uniName);
}
