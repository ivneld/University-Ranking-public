package hello.vuespring.V2.service;

import hello.vuespring.V2.domain.The;
import hello.vuespring.V2.domain.University;
import hello.vuespring.V2.repository.JdbcTemplateTheRepository;
import hello.vuespring.V2.repository.JdbcTemplateUniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TheService {

    private final JdbcTemplateTheRepository theRepository;
    private final JdbcTemplateUniversityRepository universityRepository;

    public The save(The the) {
        return theRepository.save(the);
    }

    private Integer isNullable(JSONObject object, String key) {
        Object obj = object.get(key);
        if(obj != null)
            return ((Long) obj).intValue();
        else
            return null;
    }

    public void setTheTable(String path, String year) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray jsonArray = (JSONArray) jsonObject.get(year);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);

            String name = (String) object.get("uni_name");
            Optional<University> byName = universityRepository.findByName(name);
            University university = byName.get();

            Long id = university.getId();

            Integer totRank = ((Long) object.get("tot_rank")).intValue();

            Integer accounting = isNullable(object, "Accounting & Finance");
            Integer agriculture = isNullable(object, "Agriculture & Forestry");
            Integer archaeology = isNullable(object, "Archaeology");
            Integer architecture = isNullable(object, "Architecture");
            Integer art = isNullable(object, "Art, Performing Arts & Design");
            Integer biological = isNullable(object, "Biological Sciences");
            Integer business = isNullable(object, "Business & Management");
            Integer chemical = isNullable(object, "Chemical Engineering");
            Integer chemistry = isNullable(object, "Chemistry");
            Integer civil = isNullable(object, "Civil Engineering");
            Integer communication = isNullable(object, "Communication & Media Studies");
            Integer computer = isNullable(object, "Computer Science");
            Integer economics = isNullable(object, "Economics & Econometrics");
            Integer education = isNullable(object, "Education");
            Integer electrical = isNullable(object, "Electrical & Electronic Engineering");
            Integer general = isNullable(object, "General Engineering");
            Integer geography = isNullable(object, "Geography");
            Integer geology = isNullable(object, "Geology, Environmental, Earth & Marine Sciences");
            Integer history = isNullable(object, "History, Philosophy & Theology");
            Integer languages = isNullable(object, "Languages, Literature & Linguistics");
            Integer law = isNullable(object, "Law");
            Integer mathematics = isNullable(object, "Mathematics & Statistics");
            Integer mechanical = isNullable(object, "Mechanical & Aerospace Engineering");
            Integer medicine = isNullable(object, "Medicine & Dentistry");
            Integer other = isNullable(object, "Other Health");
            Integer physics = isNullable(object, "Physics & Astronomy");
            Integer politics = isNullable(object, "Politics & International Studies (incl Development Studies)");
            Integer psychology = isNullable(object, "Psychology");
            Integer sociology = isNullable(object, "Sociology");
            Integer sport = isNullable(object, "Sport Science");

            The the = new The(id, totRank, accounting, agriculture, archaeology, architecture, art, biological, business, chemical, chemistry, civil, communication, computer, economics, education, electrical, general, geography, geology, history, languages, law, mathematics, mechanical, medicine, other, physics, politics, psychology, sociology, sport, Integer.parseInt(year));

            save(the);
        }
    }

//    @PostConstruct
//    @Transactional
//    void test() throws IOException, ParseException {
//        setTheTable("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_combined_data.json", "2022");
//        setTheTable("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_university_info_2021.json", "2021");
//        setTheTable("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_university_info_2020.json", "2020");
//    }
}
