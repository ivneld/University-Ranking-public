package hello.vuespring.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.vuespring.api.dto.QTempDto;
import hello.vuespring.api.dto.TempDto;
import hello.vuespring.repository.dao.DuplicateUniversityDao;

import javax.persistence.EntityManager;
import java.util.List;

import static hello.vuespring.entity.QUniversity.*;


public class UniversityRepositoryImpl implements UniversityRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UniversityRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<TempDto> findAllInfo() {

        return queryFactory
                .select(new QTempDto(university.id, university.name, university.engName, university.citation, university.compRate, university.intro, university.sfRatio, university.totStud, university.tuition, university.website))
                .from(university)
                .fetch();
    }

    @Override
    public List<String> findDuplicate() {

        return queryFactory
                .select(university.name)
                .from(university)
                .groupBy(university.name)
                .having(university.name.count().gt(1))
                .fetch();
    }

}
