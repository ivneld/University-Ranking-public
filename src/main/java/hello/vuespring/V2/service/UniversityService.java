package hello.vuespring.V2.service;

import hello.vuespring.V2.domain.Qs;
import hello.vuespring.V2.domain.The;
import hello.vuespring.V2.domain.University;
import hello.vuespring.V2.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UniversityService {

    private final JdbcTemplateUniversityRepository universityRepository;
    private final JdbcTemplateQsRepository qsRepository;
    private final JdbcTemplateTheRepository theRepository;

    public University save(University university) {
        return universityRepository.save(university);
    }

    public Optional<University> findById(Long id) {
        return universityRepository.findById(id);
    }

    public List<University> selectAll() {
        return universityRepository.selectAll();
    }

    /**
     * resources/recent/qs_combined_data.json 에 있는 university 정보를 저장
     *
     * @throws IOException
     * @throws ParseException
     */
    public void setUniversityInfo() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Reader reader = new FileReader("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_combined_data.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray array = (JSONArray) jsonObject.get("2022");

        for (int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            String uniName = (String) object.get("uni_name");
            String engName = (String) object.get("eng_name");
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

            University university = new University(uniName, engName, citation, compRate, intro, sfRatio, totStud, tuition, website);

            save(university);
        }

        reader = new FileReader("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_combined_data.json");
        JSONObject jsonObject1 = (JSONObject) parser.parse(reader);

        JSONArray jsonArray = (JSONArray) jsonObject1.get("2022");

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);

            String name = (String) jsonObject2.get("uni_name");
            Optional<University> byName = universityRepository.findByName(name);


            if (byName.isEmpty()) {
                String uniName = (String) jsonObject2.get("uni_name");
                String citation = (String) jsonObject2.get("citation");
                String compRate = Double.toString((Double) jsonObject2.get("competition"));
                String intro = (String) jsonObject2.get("description");
                String sfRatio = Double.toString((Double) jsonObject2.get("sf_ratio"));

                String tot_stud = (String) jsonObject2.get("tot_stud");
                Integer totStud = Integer.parseInt(tot_stud.replaceAll(",", ""));

                String tui = (String) jsonObject2.get("tuition");
                tui = tui.replaceAll(",", "");
                Integer tuition = toIntExact(round(Double.parseDouble(tui)));

                String website = (String) jsonObject2.get("website");

                University university2 = new University(uniName, "", citation, compRate, intro, sfRatio, totStud, tuition, website);

                save(university2);
            }
        }
    }

    public JSONArray JsonUniversityList(List<University> universities) {
        JSONArray array = new JSONArray();

        for (University university : universities) {
            JSONObject object = new JSONObject();

            object.put("id", university.getId());
            object.put("name", university.getName());
            object.put("engName", university.getEngName());
            object.put("citation", university.getCitation());
            object.put("compRate", university.getCompRate());
            object.put("intro", university.getIntro());
            object.put("SFRatio", university.getSfRatio());
            object.put("TotStud", university.getTotStud());
            object.put("tuition", university.getTuition());
            object.put("website", university.getWebsite());

            JSONArray qs_subjects = new JSONArray();
            qs_subjects.add("ah_rank");
            qs_subjects.add("et_rank");
            qs_subjects.add("lm_rank");
            qs_subjects.add("ns_rank");
            qs_subjects.add("sm_rank");

            JSONArray the_subjects = new JSONArray();
            the_subjects.add("accounting");
            the_subjects.add("agriculture");
            the_subjects.add("archaeology");
            the_subjects.add("architecture");
            the_subjects.add("art");


            object.put("qs_subjects", qs_subjects);
            object.put("the_subjects", the_subjects);

            JSONObject source = new JSONObject();

            // qs json structure
            List<Qs> qsList = qsRepository.findByUniversity(university);

            JSONObject qs_json = new JSONObject();
            for (Qs qs : qsList) {
                JSONObject subject = new JSONObject();
                subject.put("ah_rank", qs.getAhRank());
                subject.put("et_rank", qs.getEtRank());
                subject.put("lm_rank", qs.getLmRank());
                subject.put("ns_rank", qs.getNsRank());
                subject.put("sm_rank", qs.getSm_rank());

                JSONObject year = new JSONObject();
                year.put("rank", qs.getTotRank());
                year.put("subject", subject);

                qs_json.put(qs.getYear().toString(), year);
            }
            source.put("QS", qs_json);

            // the json structure
            List<The> theList = theRepository.findByUniversity(university);

            JSONObject the_json = new JSONObject();
            for (The the : theList) {
                JSONObject subject = new JSONObject();
                subject.put("accounting", the.getAccounting());
                subject.put("agriculture", the.getAgriculture());
                subject.put("archaeology", the.getArchaeology());
                subject.put("architecture", the.getArchitecture());
                subject.put("art", the.getArt());
                subject.put("biological", the.getBiological());
                subject.put("business", the.getBusiness());
                subject.put("chemical", the.getChemical());
                subject.put("chemistry", the.getChemistry());
                subject.put("civil", the.getCivil());
                subject.put("communication", the.getCommunication());
                subject.put("computer", the.getComputer());
                subject.put("economics", the.getEconomics());
                subject.put("education", the.getEducation());
                subject.put("electrical", the.getElectrical());
                subject.put("general", the.getGeneral());
                subject.put("geography", the.getGeography());
                subject.put("geology", the.getGeology());
                subject.put("history", the.getHistory());
                subject.put("languages", the.getLanguages());
                subject.put("law", the.getLaw());
                subject.put("mathematics", the.getMathematics());
                subject.put("mechanical", the.getMechanical());
                subject.put("medicine", the.getMedicine());
                subject.put("other", the.getOther());
                subject.put("physics", the.getPhysics());
                subject.put("politics", the.getPolitics());
                subject.put("psychology", the.getPsychology());
                subject.put("sport", the.getSport());

                JSONObject year = new JSONObject();
                year.put("rank", the.getTot_rank());
                year.put("subject", subject);

                the_json.put(the.getYear().toString(), year);
            }
            source.put("THE", the_json);

            object.put("source", source);

            array.add(object);
        }

        return array;
    }

//    @PostConstruct
//    @Transactional
//    void test() throws IOException, ParseException {
//        setUniversityInfo();
//    }

}