package hello.vuespring.V2.repository;

import hello.vuespring.V2.domain.Qs;
import hello.vuespring.V2.domain.University;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.LongFunction;

@Slf4j
@Repository
public class QsRepositoryV1 implements JdbcTemplateQsRepository{

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public QsRepositoryV1(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("qs")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Qs save(Qs qs) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(qs);
        Number key = jdbcInsert.executeAndReturnKey(param);
        qs.setId(key.longValue());
        return qs;
    }

    /**
     * qs의 Id 와 년도를 넘겨 일치하는 qs 객체 반환
     * @param cond
     * @return
     */
    @Override
    public Optional<Qs> findByCond(QsSearchCond cond) {
        String sql = "select tot_rank, ah_rank, lm_rank, ns_rank, sm_rank from qs where id=:qsId, year=:year";
        SqlParameterSource param = new BeanPropertySqlParameterSource(cond);

        try {
            Qs qs = template.queryForObject(sql, param, qsRowMapper());
            return Optional.of(qs);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Qs> findByUniversity(University university) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(university);

        String sql = "select * from qs where university_id=:id";

        return template.query(sql,param, qsRowMapper());
    }

    private RowMapper<Qs> qsRowMapper() {
        return BeanPropertyRowMapper.newInstance(Qs.class);
    }
}
