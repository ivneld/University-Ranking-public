package hello.vuespring.data.json;

import hello.vuespring.entity.Qs;
import hello.vuespring.entity.The;
import hello.vuespring.entity.University;
import hello.vuespring.repository.UniversityRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

public class JsonParsing {

    public List<University> initUniversityList(String qsPath, String thePath, Integer year) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(qsPath);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        List<University> universities = new ArrayList<>();


        JSONArray array = (JSONArray) jsonObject.get(year.toString());
        for (Object o : array) {
            universities.add(setQsInUniversity(o));
        }

        reader = new FileReader(thePath);
        jsonObject = (JSONObject) parser.parse(reader);

        array = (JSONArray) jsonObject.get(year.toString());
        for (Object o : array) {
            JSONObject object = (JSONObject) o;
            String uniName = (String) object.get("uni_name");

            if (findUniversity(universities, uniName) == null) {
                universities.add(setTheInUniversity(o));
            }
        }

        return universities;
    }

    public Optional<Qs> initQs(University university, String path, Integer year) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray jsonArray = (JSONArray) jsonObject.get(year.toString());

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            String uniName = (String) object.get("uni_name");

            if (uniName.equals(university.getName())) {
                return getQs(university, year, object);
            }
        }
        return Optional.empty();
    }

    public Optional<The> initThe(University university, String path, Integer year) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray jsonArray = (JSONArray) jsonObject.get(year.toString());

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            String uniName = (String) object.get("uni_name");

            if (uniName.equals(university.getName())) {
                return getThe(university, year, object);
            }
        }
        return Optional.empty();
    }

    private static Optional<The> getThe(University university, Integer year, JSONObject object) {
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

        return Optional.of(new The(totRank, accounting, agriculture, archaeology, architecture, art, biological, business, chemical, chemistry, civil, communication, computer, economics, education, electrical, general, geography, geology, history, languages, law, mathematics, mechanical, medicine, other, physics, politics, psychology, sociology, sport, year, university));
    }
    private static Optional<Qs> getQs(University university, Integer year, JSONObject object) {
        Integer totRank = ((Long) object.get("tot_rank")).intValue();

        Integer ahRank = isNullable(object, "AH_rank");

        Integer etRank = isNullable(object, "ET_rank");

        Integer lmRank = isNullable(object, "LM_rank");

        Integer nsRank = isNullable(object, "NS_rank");

        Integer smRank = isNullable(object, "SM_rank");

        return Optional.of(new Qs(totRank, ahRank, etRank, lmRank, nsRank, smRank, year, university));
    }

    private static Integer isNullable(JSONObject object, String key) {
        Object obj = object.get(key);
        if(obj != null)
            return ((Long) obj).intValue();
        else
            return null;
    }
    private static University setTheInUniversity(Object o) {
        JSONObject object = (JSONObject) o;
        String uniName = (String) object.get("uni_name");
        String citation = (String) object.get("citation");
        String compRate = Double.toString((Double) object.get("competition"));
        String intro = (String) object.get("description");
        String sfRatio = Double.toString((Double) object.get("sf_ratio"));

        String tot_stud = (String) object.get("tot_stud");
        Integer totStud = Integer.parseInt(tot_stud.replaceAll(",", ""));

        String tui = (String) object.get("tuition");
        tui = tui.replaceAll(",", "");
        Integer tuition = toIntExact(round(Double.parseDouble(tui)));

        String website = (String) object.get("website");

        return new University(uniName, null, citation, compRate, intro, sfRatio, totStud, tuition, website);
    }
    private static University setQsInUniversity(Object o) {
        JSONObject object = (JSONObject) o;
        String name = (String) object.get("uni_name");
        String engName = (String) object.get("eng_name");
        String citation = (String) object.get("citation");
        String compRate = Double.toString((Double) object.get("competition"));
        String description = (String) object.get("description");
        String sfRatio = Double.toString((Double) object.get("sf_ratio"));
        String tot_stud = (String) object.get("tot_stud");
        Integer totStud = Integer.parseInt(tot_stud.replaceAll(",", ""));
        String tui = (String) object.get("tuition");
        tui = tui.replaceAll(",", "");
        Integer tuition = toIntExact(round(Double.parseDouble(tui)));
        String website = (String) object.get("website");

        return  new University(name, engName, citation, compRate, description, sfRatio, totStud, tuition, website);
    }


    private static University findUniversity(List<University> list, String uniName) {
        for (University university : list) {
            if(university.getName().equals(uniName))
                return university;
        }
        return null;
    }
}
