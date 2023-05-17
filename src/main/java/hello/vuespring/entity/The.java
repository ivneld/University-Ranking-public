package hello.vuespring.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Table(name = "the")
//@Entity(name = "the")
//@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class The {

    @Id
    @GeneratedValue
    @Column(name = "the_id")
    private Long id;

    private Integer totRank;
    private Integer accounting;
    private Integer agriculture;
    private Integer archaeology;
    private Integer architecture;
    private Integer art;
    private Integer biological;
    private Integer business;
    private Integer chemical;
    private Integer chemistry;
    private Integer civil;
    private Integer communication;
    private Integer computer;
    private Integer economics;
    private Integer education;
    private Integer electrical;
    private Integer general;
    private Integer geography;
    private Integer geology;
    private Integer history;
    private Integer languages;
    private Integer law;
    private Integer mathematics;
    private Integer mechanical;
    private Integer medicine;
    private Integer other;
    private Integer physics;
    private Integer politics;
    private Integer psychology;
    private Integer sociology;
    private Integer sport;
    private Integer dataYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    public The(Integer totRank, Integer accounting, Integer agriculture, Integer archaeology, Integer architecture, Integer art, Integer biological, Integer business, Integer chemical, Integer chemistry, Integer civil, Integer communication, Integer computer, Integer economics, Integer education, Integer electrical, Integer general, Integer geography, Integer geology, Integer history, Integer languages, Integer law, Integer mathematics, Integer mechanical, Integer medicine, Integer other, Integer physics, Integer politics, Integer psychology, Integer sociology, Integer sport, Integer dataYear, University university) {
        this.totRank = totRank;
        this.accounting = accounting;
        this.agriculture = agriculture;
        this.archaeology = archaeology;
        this.architecture = architecture;
        this.art = art;
        this.biological = biological;
        this.business = business;
        this.chemical = chemical;
        this.chemistry = chemistry;
        this.civil = civil;
        this.communication = communication;
        this.computer = computer;
        this.economics = economics;
        this.education = education;
        this.electrical = electrical;
        this.general = general;
        this.geography = geography;
        this.geology = geology;
        this.history = history;
        this.languages = languages;
        this.law = law;
        this.mathematics = mathematics;
        this.mechanical = mechanical;
        this.medicine = medicine;
        this.other = other;
        this.physics = physics;
        this.politics = politics;
        this.psychology = psychology;
        this.sociology = sociology;
        this.sport = sport;
        this.dataYear = dataYear;
        setUniversity(university);
    }

    public void setUniversity(University university) {
        this.university = university;
        university.getTheList().add(this);
    }
}
