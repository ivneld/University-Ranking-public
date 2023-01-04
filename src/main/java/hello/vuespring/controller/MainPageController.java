package hello.vuespring.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.zaxxer.hikari.HikariDataSource;
import hello.vuespring.connection.ConnectionConst;
import hello.vuespring.repository.University;
import hello.vuespring.repository.UniversityRepositoryV0;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

import static hello.vuespring.connection.ConnectionConst.*;

@Controller
@RequestMapping("/")
public class MainPageController {


//    @ResponseBody
//    @RequestMapping(value = "/universities", produces = "application/json;charset=UTF-8")
//    public JSONObject mainPage() throws IOException, ParseException {
//        University university = new University();
//        JSONObject object = university.getJsonObject();
//        System.out.println("object = " + object);
//        return object;
//    }

    UniversityRepositoryV0 repository;


    @ResponseBody
    @RequestMapping(value = "/universities", produces = "application/json;charset=UTF-8")
    public JSONObject mainPage() throws SQLException, ParseException, InterruptedException {
        setRepository();
        JSONObject object = repository.selectAll();
        return object;
    }

    public void setRepository() throws InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        Thread.sleep(1000);

        repository = new UniversityRepositoryV0(dataSource);
    }
}
