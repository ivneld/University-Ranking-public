package hello.vuespring.repository;

import hello.vuespring.entity.University;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@SpringBootTest
@Transactional
class UniversityRepositoryTest {

    @Autowired
    UniversityRepository universityRepository;


    @Test
    public void test() {
        List<University> all = universityRepository.findAll();
        for (University university : all) {
            log.info("university={}", university.getName());
        }
    }
}