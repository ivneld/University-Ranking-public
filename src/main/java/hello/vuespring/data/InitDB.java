package hello.vuespring.data;

import hello.vuespring.data.json.JsonParsing;
import hello.vuespring.entity.*;
import hello.vuespring.repository.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        private final UniversityRepository universityRepository;
        private final QsRepository qsRepository;
        private final TheRepository theRepository;
        private final MajorRepository majorRepository;
        private final OverallRankRepository overallRankRepository;
        private final FilePath filePath;
        JsonParsing jsonParsing = new JsonParsing();

        public void dbInit() {
            try {
                List<University> universities = jsonParsing.initUniversityList(filePath.getQsPath(), filePath.getThePath(), 2022);
                for (University university : universities) {
                    universityRepository.save(university);
                    saveQs(university, filePath); //FilePath 객체 전달로 filePath 불러오기
                    saveThe(university, filePath);
                }
                initMajor(filePath);
                initOverallTest(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        private void saveQs(University university, FilePath filePath) throws IOException, ParseException {
            Optional<Qs> qs2022 = jsonParsing.initQs(university, filePath.getQsPath(), 2022);
            Optional<Qs> qs2021 = jsonParsing.initQs(university, filePath.getQsPath_2021(), 2021);
            Optional<Qs> qs2020 = jsonParsing.initQs(university, filePath.getQsPath_2020(), 2020);

            if(qs2022.isPresent())
                qsRepository.save(qs2022.get());
            if(qs2021.isPresent())
                qsRepository.save(qs2021.get());
            if(qs2020.isPresent())
                qsRepository.save(qs2020.get());
        }
        private void saveThe(University university, FilePath filePath) throws IOException, ParseException {
            Optional<The> the2022 = jsonParsing.initThe(university, filePath.getThePath(), 2022);
            Optional<The> the2021 = jsonParsing.initThe(university, filePath.getThePath_2021(), 2021);
            Optional<The> the2020 = jsonParsing.initThe(university, filePath.getThePath_2020(), 2020);

            if(the2022.isPresent())
                theRepository.save(the2022.get());
            if(the2021.isPresent())
                theRepository.save(the2021.get());
            if(the2020.isPresent())
                theRepository.save(the2020.get());
        }
        public void initMajor(FilePath filePath) throws IOException, ParseException {
            File dir = new File(filePath.getFolderPath());

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
        private static Integer isNullable(JSONObject object, String key) {
            Object obj = object.get(key);
            if(obj != null)
                return ((Long) obj).intValue();
            else
                return null;
        }
        public void initOverallTest(FilePath filePath) throws IOException, ParseException {
            ArrayList<OverallRank> result = new ArrayList<>();
            File dir = new File(filePath.getFolderPath());

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

                        if (universityRepository.findByName(institution) == null) {
                            String korName = findKorName(parser, institution);
                            universityRepository.save(new University(institution, korName));
                        }

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
        private static String findKorName(JSONParser parser, String institution) throws IOException, ParseException {
            String forNoNameFilePath = "/Users/kimyuseong/study/vue-springV2/src/test/resources/for_none_kor_name.json";
            FileReader temp = new FileReader(forNoNameFilePath);
            JSONArray parse = (JSONArray) parser.parse(temp);

            String korName="";
            for (Object o1 : parse) {
                JSONObject object = (JSONObject) o1;
                if(object.get("eng_name").equals(institution))
                    korName = (String)object.get("kor_name");
            }
            return korName;
        }
    }
}