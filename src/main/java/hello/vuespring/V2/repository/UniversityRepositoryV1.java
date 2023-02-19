package hello.vuespring.V2.repository;

import hello.vuespring.V2.domain.University;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Repository
public class UniversityRepositoryV1 implements JdbcTemplateUniversityRepository{

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public UniversityRepositoryV1(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("university")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public University save(University university) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(university);
        Number key = jdbcInsert.executeAndReturnKey(param);
        university.setId(key.longValue());
        return university;
    }

    /**
     * 사용 금지
     * @param universityId
     * @param updateParam
     */
    @Override
    public void update(Long universityId, UniversityUpdateDto updateParam) {
        String sql = "update university set tot_stud=:totStud, tuition=:tuition where id=:";
    }

    @Override
    public Optional<University> findById(Long id) {
        String sql = "select * from university where id=:id";
        try {
            Map<String, Object> param = Map.of("id", id);
            University university = template.queryForObject(sql, param, universityRowMapper());
            return Optional.of(university);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<University> selectAll() {
        String sql = "select * from university";
        return template.query(sql, universityRowMapper());
    }

    @Override
    public Optional<University> findByName(String uniName) {
        String sql = "select * from university where name=:uniName";
        try {
            Map<String, Object> param = Map.of("uniName", uniName);
            University university = template.queryForObject(sql, param, universityRowMapper());
            return Optional.of(university);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<University> universityRowMapper() {
        return BeanPropertyRowMapper.newInstance(University.class); //camel 변환 지원
    } 
}
