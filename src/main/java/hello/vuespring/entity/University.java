package hello.vuespring.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "university")
@Entity(name = "university")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class University {

    @Id
    @GeneratedValue
    @Column(name = "university_id")
    private Long id;

    private String name;
    private String engName;

    @Lob
    private String citation;
    private String compRate;

    @Lob
    private String intro;
    private String sfRatio;
    private Integer totStud;
    private Integer tuition;
    private String website;

    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Qs> qsList = new ArrayList<>();

    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<The> theList = new ArrayList<>();

    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<OverallRank> overallRanks = new ArrayList<>();

    @OneToOne(mappedBy = "university", cascade = CascadeType.REMOVE, orphanRemoval = true)
    UniversityDetail detail;


    public University(String name, String engName, String citation, String compRate, String intro, String sfRatio, Integer totStud, Integer tuition, String website) {
        this.name = name;
        this.engName = engName;
        this.citation = citation;
        this.compRate = compRate;
        this.intro = intro;
        this.sfRatio = sfRatio;
        this.totStud = totStud;
        this.tuition = tuition;
        this.website = website;
    }

    public University(String engName, String name) {
        this.engName = engName;
        this.name = name;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }
    @PrePersist
    public void perPersist() {
        this.citation = this.citation == null ? "-" : this.citation;
        this.compRate = this.compRate == null ? "-" : this.compRate;
        this.intro = this.intro == null ? "-" : this.intro;
        this.sfRatio = this.sfRatio == null ? "-" : this.sfRatio;
        this.totStud = this.totStud == null ? 0 : this.totStud;
        this.tuition = this.tuition == null ? 0 : this.tuition;
        this.website = this.website == null ? "-" : this.website;
    }

    public void setDetail(UniversityDetail detail) {
        this.detail = detail;
    }
}
