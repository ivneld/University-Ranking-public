package hello.vuespring.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import hello.vuespring.repository.University;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainPageController {


    @ResponseBody
    @RequestMapping(value = "/universities", produces = "application/json;charset=UTF-8")
    public JSONObject mainPage() throws IOException, ParseException {
        University university = new University();
        JSONObject object = university.getJsonObject();
        System.out.println("object = " + object);
        return object;
    }


}
