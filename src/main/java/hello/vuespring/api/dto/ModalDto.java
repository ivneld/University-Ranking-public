package hello.vuespring.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ModalDto {

    private Long id;
    private String name;
    private String engName;
    private String citation;
    private String compRate;
    private String intro;
    private String sfRatio;
    private Integer totStud;
    private Integer tuition;
    private String website;

    private List<MajorRankDto> rank;


    public ModalDto(Long id, String name, String engName, String citation, String compRate, String intro, String sfRatio, Integer totStud, Integer tuition, String website, List<MajorRankDto> rank) {
        this.id = id;
        this.name = name;
        this.engName = engName;
        this.citation = citation;
        this.compRate = compRate;
        this.intro = intro;
        this.sfRatio = sfRatio;
        this.totStud = totStud;
        this.tuition = tuition;
        this.website = website;
        this.rank = rank;
    }
}
