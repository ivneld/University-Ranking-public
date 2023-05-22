package hello.vuespring.service;

import hello.vuespring.api.dto.MainPageDto;
import hello.vuespring.api.dto.MajorRankDto;
import hello.vuespring.api.dto.ModalDto;
import hello.vuespring.api.dto.TempDto;
import hello.vuespring.entity.Major;
import hello.vuespring.entity.University;
import hello.vuespring.repository.MajorRepository;
import hello.vuespring.repository.OverallRankRepositoryCustom;
import hello.vuespring.repository.UniversityRepository;
import hello.vuespring.repository.UniversityRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final UniversityRepository universityRepository;
    private final OverallRankRepositoryCustom overallRankRepositoryCustom;
    private final UniversityRepositoryCustom universityRepositoryCustom;
    private final MajorRepository majorRepository;

    public ModalDto modalDtoService(Long id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isEmpty()) {
            return new ModalDto();
        }
        University university = optionalUniversity.get();
        List<MajorRankDto> rank = overallRankRepositoryCustom.findRankWithUniversityId(id);

        return new ModalDto(university.getId(), university.getName(), university.getEngName(), university.getCitation()
                , university.getCompRate(), university.getIntro(), university.getSfRatio(), university.getTotStud(),
                university.getTuition(), university.getWebsite(), rank);
    }

    public MainPageDto mainPageResponse(){
        return mainPageYearResponse(2023);
    }

    public MainPageDto mainPageYearResponse(Integer year) {
        List<TempDto> result = universityRepositoryCustom.findAllInfo();
        List<Major> majorList = majorRepository.findAll();
        List<String> majors = new ArrayList<>();

        for (Major major : majorList) {
            majors.add(major.getKorName());
        }
        List<TempDto> removeList = new ArrayList<>();
        for (TempDto tempDto : result) {
            List<MajorRankDto> rank = overallRankRepositoryCustom.findRankWithUniversityIdAndYear(tempDto.getUni_id(), year);
            if (rank.isEmpty()) {
                System.out.println("rank is empty!");
                removeList.add(tempDto);
            } else {
                tempDto.setRank(rank);
                for (MajorRankDto majorRankDto : rank) {
                    if (majorRankDto.getMajorName().equals("Universities")) {
                        tempDto.setTotRank(majorRankDto.getTotRank());
                        break;
                    }
                }
            }
        }
        for (TempDto tempDto : removeList) {
            result.remove(tempDto);
        }

        return new MainPageDto(result, majors);
    }
}
