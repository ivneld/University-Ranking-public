package hello.vuespring.api;

import hello.vuespring.api.dto.MainPageDto;
import hello.vuespring.entity.Qs;
import hello.vuespring.entity.The;
import hello.vuespring.entity.University;
import hello.vuespring.repository.OverallRankRepositoryCustom;
import hello.vuespring.repository.QsRepository;
import hello.vuespring.repository.TheRepository;
import hello.vuespring.repository.UniversityRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@SpringBootTest
@RestController
@RequiredArgsConstructor
class UniversityApiControllerTest {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    QsRepository qsRepository;
    @Autowired
    TheRepository theRepository;
    @Autowired
    OverallRankRepositoryCustom overallRankRepositoryCustom;

    @Test
    public void mainPageApiTest() {
        JSONArray array = new JSONArray();
        List<University> universities = universityRepository.findAll();

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

            List<Qs> qsList = qsRepository.findByUniversityId(university.getId());

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

                qs_json.put(qs.getDataYear().toString(), year);
            }
            source.put("QS", qs_json);

            List<The> theList = theRepository.findByUniversityId(university.getId());

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
                year.put("rank", the.getTotRank());
                year.put("subject", subject);

                the_json.put(the.getDataYear().toString(), year);
            }
            source.put("THE", the_json);

            object.put("source", source);

            array.add(object);
        }
        JSONObject object = new JSONObject();
        object.put("universities", array);
        JSONObject object1 = new JSONObject();
        object1.put("universities", object);

        log.info("result={}", object1);
    }

    @Test
    public void test() {
        University findUniversity = universityRepository.findOne(1L);
        List<Qs> result = qsRepository.findByUniversityId(findUniversity.getId());

        for (Qs qs : result) {
            log.info("result={}", qs.getId());
        }

        List<The> theList = theRepository.findByUniversityId(findUniversity.getId());
        for (The the : theList) {
            log.info("result={}", the.getId());
        }
    }

}
