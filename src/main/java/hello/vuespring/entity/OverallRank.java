package hello.vuespring.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Table(name = "overallRank")
@Entity(name = "overallRank")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OverallRank {

    @Id
    @GeneratedValue
    @Column(name = "rank_id")
    private Long id;

    @ColumnDefault("0")
    private Integer totRank;
    @ColumnDefault("0")
    private Integer gloRank;
    @ColumnDefault("0")
    private Integer dataYear;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    public OverallRank(Integer totRank, Integer gloRank, Integer dataYear, University university, Major major) {
        this.totRank = totRank;
        this.gloRank = gloRank;
        this.dataYear = dataYear;
        setUniversity(university);
        setMajor(major);
    }

    private void setMajor(Major major) {
        this.major = major;
        major.getOverallRanks().add(this);
    }

    private void setUniversity(University university) {
        this.university = university;
        university.getOverallRanks().add(this);
    }
}
