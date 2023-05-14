package hello.vuespring.api;

import hello.vuespring.api.dto.MainPageDto;
import hello.vuespring.api.dto.MajorRankDto;
import hello.vuespring.api.dto.ModalDto;
import hello.vuespring.api.dto.TempDto;
import hello.vuespring.entity.Major;
import hello.vuespring.repository.MajorRepository;
import hello.vuespring.repository.OverallRankRepositoryCustom;
import hello.vuespring.repository.UniversityRepositoryCustom;
import hello.vuespring.service.MainPageService;
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
    MainPageService mainPageService;

    @GetMapping("/universities")
    public MainPageDto mainPageResponse() {
        return mainPageService.mainPageResponse();
    }

    @GetMapping("universities/{uni_id}")
    public ModalDto modalPageResponse(@PathVariable("uni_id") String id) {
        return mainPageService.modalDtoService(Long.parseLong(id));
    }

    @GetMapping("universities/{year}")
    public MainPageDto mainPageYearResponse(@PathVariable("year") Integer year) {
        return mainPageService.mainPageYearResponse(year);
    }
}
