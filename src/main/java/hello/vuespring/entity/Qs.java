package hello.vuespring.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Table(name = "qs")
@Entity(name = "qs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Qs {

    @Id @GeneratedValue
    @Column(name = "qs_id")
    private Long id;

    private Integer totRank;
    private Integer ahRank;
    private Integer etRank;
    private Integer lmRank;
    private Integer nsRank;
    private Integer sm_rank;
    private Integer dataYear;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    public Qs(Integer totRank, Integer ahRank, Integer etRank, Integer lmRank, Integer nsRank, Integer sm_rank, Integer dataYear, University university) {
        this.totRank = totRank;
        this.ahRank = ahRank;
        this.etRank = etRank;
        this.lmRank = lmRank;
        this.nsRank = nsRank;
        this.sm_rank = sm_rank;
        this.dataYear = dataYear;
        setUniversity(university);
    }

    public void setUniversity(University university) {
        this.university = university;
        university.getQsList().add(this);
    }
}
