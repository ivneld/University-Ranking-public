package hello.vuespring.V2.config;

import hello.vuespring.V2.repository.*;
import hello.vuespring.V2.service.QsService;
import hello.vuespring.V2.service.TheService;
import hello.vuespring.V2.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    @Bean
    public UniversityService universityService() {
        return new UniversityService(universityRepository(), qsRepository(), theRepository());
    }

    @Bean
    public QsService qsService() {
        return new QsService(qsRepository(), universityRepository());
    }
    @Bean
    public TheService theService() {
        return new TheService(theRepository(), universityRepository());
    }
    /*@Bean
    public UniversityService universityService() {
        return new UniversityService(jdbcTemplateUniversityRepository());
    }*/

    @Bean
    public JdbcTemplateUniversityRepository universityRepository() {
        return new UniversityRepositoryV1(dataSource);
    }

    @Bean
    public JdbcTemplateQsRepository qsRepository() {
        return new QsRepositoryV1(dataSource);
    }
    @Bean
    public JdbcTemplateTheRepository theRepository() {
        return new TheRepositoryV1(dataSource);
    }
//    @Bean
//    public JdbcTemplateQsRepository jdbcTemplateQsRepository() {
//        return new QsRepositoryV1(dataSource);
//    }
//
//    @Bean
//    public JdbcTemplateTheRepository jdbcTemplateTheRepository() {
//        return new TheRepositoryV1(dataSource);
//    }

    @Bean
    public JdbcTemplateUniversityRepository jdbcTemplateUniversityRepository() {
        return new UniversityRepositoryV1(dataSource);
    }
}
