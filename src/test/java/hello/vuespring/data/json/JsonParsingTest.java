package hello.vuespring.data.json;

import hello.vuespring.entity.*;
import hello.vuespring.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ddl-auto : create -> test()
 * ddl-auto : none -> initOverallTest()
 */
@Slf4j
@SpringBootTest
@Transactional
class JsonParsingTest {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    QsRepository qsRepository;
    @Autowired
    TheRepository theRepository;
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    OverallRankRepository overallRankRepository;

    JsonParsing jsonParsing = new JsonParsing();
    String qsPath = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_combined_data.json";
    String thePath = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_combined_data.json";
    String folderPath = "/Users/kimyuseong/study/vue-springV2/src/test/resources/vue-spring data";

    @Test
    @Rollback(value = false)
    public void test() {
        try {
            List<University> universities = jsonParsing.initUniversityList(qsPath, thePath, 2022);
            for (University university : universities) {
                universityRepository.save(university);
                saveQs(university);
                saveThe(university);
            }
            initMajor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test2() throws IOException, ParseException {
        String folderPath = "/Users/kimyuseong/study/vue-springV2/src/test/resources/vue-spring data";
        File dir = new File(folderPath);

        File[] files = dir.listFiles();
        for (File file : files) {
            String filePath = file.getAbsolutePath();
            File file1 = new File(filePath);

            File[] files1 = file1.listFiles();
            for (File file2 : files1) {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(file2.getAbsolutePath());
                JSONArray array = (JSONArray) parser.parse(reader);

                for (Object o : array) {
                    JSONObject jsonObject = (JSONObject) o;
                    String institution = (String) jsonObject.get("Institution");
                    Integer rank = isNullable(jsonObject, "Rank");
                    Integer global_rank = isNullable(jsonObject, "Global Rank");

                    University university = universityRepository.findByName(institution.replace(" *", ""));
                    if(university == null)
                    log.info("find={}", file.getName());
                }
            }
        }
    }

    @Test
    @Rollback(false)
    public void initOverallTest() throws IOException, ParseException {
        ArrayList<OverallRank> result = new ArrayList<>();
        File dir = new File(folderPath);

        File[] rootFolder = dir.listFiles();
        for (File folder : rootFolder) {
            String targetPath = folder.getAbsolutePath();
            File file = new File(targetPath);

            File[] files = file.listFiles();
            for (File jsonFile : files) {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(jsonFile.getAbsolutePath());
                JSONArray array = (JSONArray) parser.parse(reader);

                for (Object o : array) {
                    JSONObject jsonObject = (JSONObject) o;
                    String institution = ((String) jsonObject.get("Institution")).replace(" *", "");
                    Integer rank = isNullable(jsonObject, "Rank");
                    Integer global_rank = isNullable(jsonObject, "Global Rank");
                    Integer year = Integer.parseInt(folder.getName());

                    if (universityRepository.findByName(institution) == null)
                        universityRepository.save(new University(institution));

                    University university = universityRepository.findByName(institution);
                    String majorName = jsonFile.getName().substring(32, jsonFile.getName().indexOf("  -"));
                    Major major = majorRepository.findByName(majorName);

                    result.add(new OverallRank(rank, global_rank, year, university, major));
                }
            }
        }

        for (OverallRank overallRank : result) {
            overallRankRepository.save(overallRank);
        }
    }

    @Test
    public void nameTest() {
        String test = "ScimagoIR 2023 - Overall Rank - Agricultural and Biological Sciences  - Universities - KOR.json";
        String substring = test.substring(32, test.indexOf("  -"));
        log.info("sub={}", substring);
    }

    public void initMajor() throws IOException, ParseException {
        File dir = new File(folderPath);

        File[] rootFolder = dir.listFiles();
        for (File folder : rootFolder) {
            String targetPath = folder.getAbsolutePath();
            File file = new File(targetPath);

            File[] files = file.listFiles();
            for (File jsonFile : files) {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(jsonFile.getAbsolutePath());
                JSONArray array = (JSONArray) parser.parse(reader);

                String majorName = jsonFile.getName().substring(32, jsonFile.getName().indexOf("  -"));
                if(majorRepository.findByName(majorName) == null)
                    majorRepository.save(new Major(majorName));
            }
        }
    }

    private void saveThe(University university) throws IOException, ParseException {
        Optional<The> the2022 = jsonParsing.initThe(university, "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_combined_data.json", 2022);
        Optional<The> the2021 = jsonParsing.initThe(university, "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_university_info_2021.json", 2021);
        Optional<The> the2020 = jsonParsing.initThe(university, "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_university_info_2020.json", 2020);

        if(the2022.isPresent())
            theRepository.save(the2022.get());
        if(the2021.isPresent())
            theRepository.save(the2021.get());
        if(the2020.isPresent())
            theRepository.save(the2020.get());
    }

    private void saveQs(University university) throws IOException, ParseException {
        Optional<Qs> qs2022 = jsonParsing.initQs(university, "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_combined_data.json", 2022);
        Optional<Qs> qs2021 = jsonParsing.initQs(university, "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_university_info_2021.json", 2021);
        Optional<Qs> qs2020 = jsonParsing.initQs(university, "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_university_info_2020.json", 2020);

        if(qs2022.isPresent())
            qsRepository.save(qs2022.get());
        if(qs2021.isPresent())
            qsRepository.save(qs2021.get());
        if(qs2020.isPresent())
            qsRepository.save(qs2020.get());
    }

    private static Integer isNullable(JSONObject object, String key) {
        Object obj = object.get(key);
        if(obj != null)
            return ((Long) obj).intValue();
        else
            return null;
    }
}