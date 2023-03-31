package hello.vuespring.repository;

import hello.vuespring.entity.University;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class UniversityRepositoryTest {

    @Autowired
    UniversityRepository universityRepository;


    @Test
    public void test() {

    }
}