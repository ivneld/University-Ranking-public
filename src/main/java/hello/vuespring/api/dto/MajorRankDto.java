package hello.vuespring.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MajorRankDto {

    private String majorName;
    private String korName;
    private Integer totRank;
    private Integer gloRank;
    private Integer year;

    @QueryProjection
    public MajorRankDto(String majorName, String korName, Integer totRank, Integer gloRank, Integer year) {
        this.majorName = majorName;
        this.korName = korName;
        this.totRank = totRank;
        this.gloRank = gloRank;
        this.year = year;
    }
}
