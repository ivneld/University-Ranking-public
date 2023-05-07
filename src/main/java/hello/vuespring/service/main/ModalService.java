package hello.vuespring.service.main;

import hello.vuespring.api.dto.MajorRankDto;
import hello.vuespring.api.dto.ModalDto;
import hello.vuespring.entity.University;
import hello.vuespring.repository.OverallRankRepositoryCustom;
import hello.vuespring.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalService {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    OverallRankRepositoryCustom overallRankRepositoryCustom;

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
}
