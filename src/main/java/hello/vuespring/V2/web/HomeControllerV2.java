package hello.vuespring.V2.web;

import hello.vuespring.V2.domain.University;
import hello.vuespring.V2.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeControllerV2 {

    @Autowired
    UniversityService service;

    @RequestMapping(value = "/universities", produces = "application/json;UTF-8")
    @ResponseBody
    public JSONObject mainPage() {
        List<University> universities = service.selectAll();
        JSONArray array = service.JsonUniversityList(universities);
        JSONObject object = new JSONObject();
        object.put("universities", array);
        return object;
    }
}
