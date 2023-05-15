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
    @ColumnDefault("0")
    private String citation;
    @ColumnDefault("0")
    private String compRate;

    @Lob
    @ColumnDefault("0")
    private String intro;
    @ColumnDefault("0")
    private String sfRatio;
    @ColumnDefault("0")
    private Integer totStud;
    @ColumnDefault("0")
    private Integer tuition;
    @ColumnDefault("0")
    private String website;

//    private String foreign;
//    private String studProfessRatio;
//    private String employment;
//    private String dormitory;
//    private String scholarship;
//    private String education;
//    private String library;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    List<Qs> qsList = new ArrayList<>();

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    List<The> theList = new ArrayList<>();

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    List<OverallRank> overallRanks = new ArrayList<>();

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
}
