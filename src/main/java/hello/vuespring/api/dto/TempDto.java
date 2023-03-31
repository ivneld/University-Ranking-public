package hello.vuespring.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TempDto {

    private Long uni_id;
    private String name;
    private String engName;
    private String citation;
    private String compRate;
    private String intro;
    private String SFRatio;
    private Integer totStud;
    private Integer tuition;
    private String website;

    private List<MajorRankDto> rank;

    @QueryProjection
    public TempDto(Long uni_id, String name, String engName, String citation, String compRate, String intro, String SFRatio, Integer totStud, Integer tuition, String website) {
        this.uni_id = uni_id;
        this.name = name;
        this.engName = engName;
        this.citation = citation;
        this.compRate = compRate;
        this.intro = intro;
        this.SFRatio = SFRatio;
        this.totStud = totStud;
        this.tuition = tuition;
        this.website = website;
    }
}
