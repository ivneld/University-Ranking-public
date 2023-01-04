package hello.vuespring.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.vuespring.connection.ConnectionConst;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static hello.vuespring.connection.ConnectionConst.*;

@Slf4j
class UniversityRepositoryV0Test {

    UniversityRepositoryV0 repository;

    @BeforeEach
    void beforeEach() throws InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        Thread.sleep(1000);

        repository = new UniversityRepositoryV0(dataSource);
    }

    @Test
    void crud() throws SQLException, ParseException {
        //selectAll
        JSONObject object = repository.selectAll();
        log.info("object={}", object);

        //string to JSON
//        String source = list.get(0).getSource();
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject)parser.parse(source);
//
//        log.info("Object={}", jsonObject);
//        JSONObject QS = (JSONObject) jsonObject.get("QS");
//
//        log.info("QS={}", QS);
//        JSONObject year = (JSONObject) QS.get("2020");
//
//        log.info("year={}", year);

    }

}