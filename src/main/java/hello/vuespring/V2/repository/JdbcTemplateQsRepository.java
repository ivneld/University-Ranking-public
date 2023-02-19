package hello.vuespring.V2.repository;

import hello.vuespring.V2.domain.Qs;
import hello.vuespring.V2.domain.University;

import java.util.List;
import java.util.Optional;

public interface JdbcTemplateQsRepository {

    Qs save(Qs qs);

    Optional<Qs> findByCond(QsSearchCond cond);

    public List<Qs> findByUniversity(University university);
}
