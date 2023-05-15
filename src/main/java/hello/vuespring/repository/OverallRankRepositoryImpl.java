package hello.vuespring.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.vuespring.api.dto.MainPageDto;
import hello.vuespring.api.dto.MajorRankDto;
import hello.vuespring.api.dto.QMajorRankDto;
import hello.vuespring.entity.QMajor;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.util.List;

import static hello.vuespring.entity.QMajor.*;
import static hello.vuespring.entity.QOverallRank.*;
import static hello.vuespring.entity.QUniversity.*;

public class OverallRankRepositoryImpl implements OverallRankRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OverallRankRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MajorRankDto> findRankWithUniversityId(@Param("id") Long id) {
        return queryFactory
                .select(new QMajorRankDto(major.name,major.korName, overallRank.totRank, overallRank.gloRank, overallRank.dataYear))
                .from(overallRank)
                .leftJoin(major).on(overallRank.major.id.eq(major.id))
                .where(overallRank.university.id.eq(id))
                .fetch();
    }

    @Override
    public List<MajorRankDto> findRankWithUniversityIdAndYear(Long id, Integer year) {
        return queryFactory
                .select(new QMajorRankDto(major.name, major.korName, overallRank.totRank, overallRank.gloRank, overallRank.dataYear))
                .from(overallRank)
                .leftJoin(major).on(overallRank.major.id.eq(major.id))
                .where(overallRank.university.id.eq(id).and(overallRank.dataYear.eq(year)))
                .fetch();
    }
}
