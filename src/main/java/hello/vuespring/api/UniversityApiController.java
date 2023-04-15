package hello.vuespring.api;

import hello.vuespring.api.dto.MainPageDto;
import hello.vuespring.api.dto.MajorRankDto;
import hello.vuespring.api.dto.TempDto;
import hello.vuespring.entity.Major;
import hello.vuespring.repository.MajorRepository;
import hello.vuespring.repository.OverallRankRepositoryCustom;
import hello.vuespring.repository.UniversityRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://k-ranking.co.kr")
public class UniversityApiController {

    @Autowired
    OverallRankRepositoryCustom overallRankRepositoryCustom;
    @Autowired
    UniversityRepositoryCustom universityRepositoryCustom;
    @Autowired
    MajorRepository majorRepository;

    @GetMapping("/universities")
    public MainPageDto mainPageDto() {
        List<TempDto> result = universityRepositoryCustom.findAllInfo();
        List<Major> majorList = majorRepository.findAll();
        List<String> majors = new ArrayList<>();

        for (Major major : majorList) {
            majors.add(major.getName());
        }
        for (TempDto tempDto : result) {
            List<MajorRankDto> rank = overallRankRepositoryCustom.findRankWithUniversityId(tempDto.getUni_id());
            tempDto.setRank(rank);
        }


        return new MainPageDto(result, majors);
    }
}
