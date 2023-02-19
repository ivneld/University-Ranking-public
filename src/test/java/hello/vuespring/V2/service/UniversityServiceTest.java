package hello.vuespring.V2.service;

import hello.vuespring.V2.domain.Qs;
import hello.vuespring.V2.domain.University;
import hello.vuespring.V2.repository.JdbcTemplateQsRepository;
import hello.vuespring.V2.repository.JdbcTemplateTheRepository;
import hello.vuespring.V2.repository.JdbcTemplateUniversityRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
//@Transactional
@SpringBootTest
class UniversityServiceTest {

    @Autowired
    JdbcTemplateUniversityRepository universityRepository;

    @Autowired
    JdbcTemplateQsRepository qsRepository;
    @Autowired
    JdbcTemplateTheRepository theRepository;

    @Autowired
    UniversityService service;


    @Test
    void test() {
        List<University> universities = universityRepository.selectAll();
        JSONArray array = service.JsonUniversityList(universities);

        JSONObject object = new JSONObject();
        object.put("universities", array);
        JSONObject object1 = new JSONObject();
        object1.put("universities", object);
        log.info("log={}", object1);
    }
}