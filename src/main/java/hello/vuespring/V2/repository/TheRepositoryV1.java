package hello.vuespring.V2.repository;

import hello.vuespring.V2.domain.Qs;
import hello.vuespring.V2.domain.The;
import hello.vuespring.V2.domain.University;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class TheRepositoryV1 implements JdbcTemplateTheRepository{

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public TheRepositoryV1(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("the")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public The save(The the) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(the);
        Number key = jdbcInsert.executeAndReturnKey(param);
        the.setId(key.longValue());
        return the;
    }

    /**
     * qs의 Id 와 년도를 넘겨 일치하는 qs 객체 반환
     * @param cond
     * @return
     */
    @Override
    public Optional<The> findByCond(TheSearchCond cond) {
        String sql = "select * from the where id=:theId, year=:year";
        SqlParameterSource param = new BeanPropertySqlParameterSource(cond);

        try {
            The the = template.queryForObject(sql, param, theRowMapper());
            return Optional.of(the);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<The> findByUniversity(University university) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(university);

        String sql = "select * from the where university_id=:id";
        return template.query(sql,param,  theRowMapper());
    }

    private RowMapper<The> theRowMapper() {
        return BeanPropertyRowMapper.newInstance(The.class);
    }
}
