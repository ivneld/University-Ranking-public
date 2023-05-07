package hello.vuespring.api;

import hello.vuespring.api.dto.MainPageDto;
import hello.vuespring.api.dto.MajorRankDto;
import hello.vuespring.api.dto.ModalDto;
import hello.vuespring.api.dto.TempDto;
import hello.vuespring.entity.Major;
import hello.vuespring.repository.MajorRepository;
import hello.vuespring.repository.OverallRankRepositoryCustom;
import hello.vuespring.repository.UniversityRepositoryCustom;
import hello.vuespring.service.main.ModalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UniversityApiController {

    @Autowired
    OverallRankRepositoryCustom overallRankRepositoryCustom;
    @Autowired
    UniversityRepositoryCustom universityRepositoryCustom;
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    ModalService modalService;

    @GetMapping("/universities")
    public MainPageDto mainPageResponse() {
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

    @GetMapping("universities/{uni_id}")
    public ModalDto modalPageResponse(@PathVariable("uni_id") String id) {
        return modalService.modalDtoService(Long.parseLong(id));
    }
}
