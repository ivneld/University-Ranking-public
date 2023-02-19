package hello.vuespring.V2.repository;

import hello.vuespring.V2.domain.The;
import hello.vuespring.V2.domain.University;

import java.util.List;
import java.util.Optional;

public interface JdbcTemplateTheRepository {
    The save(The the);

    Optional<The> findByCond(TheSearchCond cond);

    public List<The> findByUniversity(University university);
}
