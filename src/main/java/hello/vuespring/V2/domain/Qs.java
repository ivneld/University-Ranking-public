package hello.vuespring.V2.domain;

import lombok.Data;

@Data
public class Qs {
    private Long id;
    private Long universityId;
    private Integer totRank;
    private Integer ahRank;
    private Integer etRank;
    private Integer lmRank;
    private Integer nsRank;
    private Integer sm_rank;
    private Integer year;

    public Qs() {

    }

    public Qs(Long universityId, Integer totRank, Integer ahRank, Integer etRank,Integer lmRank, Integer nsRank, Integer sm_rank, Integer year) {
        this.universityId = universityId;
        this.totRank = totRank;
        this.ahRank = ahRank;
        this.etRank = etRank;
        this.lmRank = lmRank;
        this.nsRank = nsRank;
        this.sm_rank = sm_rank;
        this.year = year;
    }

    public Qs(Long id, Long universityId, Integer totRank, Integer ahRank, Integer etRank, Integer lmRank, Integer nsRank, Integer sm_rank, Integer year) {
        this.id = id;
        this.universityId = universityId;
        this.totRank = totRank;
        this.ahRank = ahRank;
        this.etRank = etRank;
        this.lmRank = lmRank;
        this.nsRank = nsRank;
        this.sm_rank = sm_rank;
        this.year = year;
    }
}
