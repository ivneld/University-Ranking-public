package hello.vuespring.V2.service;

import hello.vuespring.V2.domain.Qs;
import hello.vuespring.V2.domain.University;
import hello.vuespring.V2.repository.JdbcTemplateQsRepository;
import hello.vuespring.V2.repository.JdbcTemplateUniversityRepository;
import hello.vuespring.V2.repository.QsSearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QsService {

    private final JdbcTemplateQsRepository qsRepository;
    private final JdbcTemplateUniversityRepository universityRepository;

    public Qs save(Qs qs) {
        return qsRepository.save(qs);
    }

    public Optional<Qs> findByCond(QsSearchCond cond) {
        return qsRepository.findByCond(cond);
    }

    public List<Qs> findByUniversity(University university) {
        return qsRepository.findByUniversity(university);
    }
    /**
     * json 파일 경로와 연도를 인자로 받아 파싱하여 디비에 저장
     * @param path
     * @param year
     * @throws IOException
     * @throws ParseException
     */
    public void setQsTable(String path, String year) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray jsonArray = (JSONArray) jsonObject.get(year);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);

//            log.info("list={}",object);
            // uni_name 과 일치하는 university_id
            String name = (String) object.get("uni_name");
            Optional<University> byName = universityRepository.findByName(name);
            University university = byName.get();

            Long id = university.getId();
            
            Integer totRank = ((Long) object.get("tot_rank")).intValue();

            Integer ahRank = null;
            Object ah_rank = object.get("AH_rank");
            if(ah_rank != null)
                ahRank = ((Long) object.get("AH_rank")).intValue();

            Integer etRank = null;
            Object et_rank = object.get("ET_rank");
            if(et_rank != null)
                etRank = ((Long) object.get("ET_rank")).intValue();

            Integer lmRank = null;
            Object lm_rank = object.get("LM_rank");
            if(lm_rank != null)
                lmRank = ((Long) object.get("LM_rank")).intValue();

            Integer nsRank = null;
            Object ns_rank = object.get("NS_rank");
            if(ns_rank != null)
                nsRank = ((Long) object.get("NS_rank")).intValue();

            Integer smRank = null;
            Object sm_rank = object.get("SM_rank");
            if(sm_rank != null)
                smRank = ((Long) object.get("SM_rank")).intValue();

            Qs qs = new Qs(id, totRank, ahRank, etRank, lmRank, nsRank, smRank, Integer.parseInt(year));

            save(qs);
        }
    }

    /**
     * combined 파일은 형식이 약간 달라 별도의 메서드 이용
     * @throws IOException
     * @throws ParseException
     */
    public void setQsTableWithRecent() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Reader reader = new FileReader("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/recent/QS_combined_data.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray jsonArray = (JSONArray) jsonObject.get("combined");

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);

            // uni_name 과 일치하는 university_id
            String name = (String) object.get("uni_name");
            Optional<University> byName = universityRepository.findByName(name);
            University university = byName.get();

            Long id = university.getId();


            Integer totRank = ((Long) object.get("tot_rank")).intValue();

            Integer ahRank = null;
            Object ah_rank = object.get("AH_rank");
            if(ah_rank != null)
                ahRank = ((Long) object.get("AH_rank")).intValue();

            Integer etRank = null;
            Object et_rank = object.get("ET_rank");
            if(et_rank != null)
                etRank = ((Long) object.get("ET_rank")).intValue();

            Integer lmRank = null;
            Object lm_rank = object.get("LM_rank");
            if(lm_rank != null)
                lmRank = ((Long) object.get("LM_rank")).intValue();

            Integer nsRank = null;
            Object ns_rank = object.get("NS_rank");
            if(ns_rank != null)
                nsRank = ((Long) object.get("NS_rank")).intValue();

            Integer smRank = null;
            Object sm_rank = object.get("SM_rank");
            if(sm_rank != null)
                smRank = ((Long) object.get("SM_rank")).intValue();

            Integer year = ((Long) object.get("year")).intValue();

            Qs qs = new Qs(id, totRank, ahRank, etRank, lmRank, nsRank, smRank, year);

            save(qs);
        }

    }
    
//    @PostConstruct
//    @Transactional
//    void test() throws IOException, ParseException {
//
//        setQsTable("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_combined_data.json","2022");
//        setQsTable("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_university_info_2021.json", "2021");
//        setQsTable("/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_university_info_2020.json", "2020");
//    }
}
