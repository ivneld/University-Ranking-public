package hello.vuespring.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Table(
        name = "UniversityDetail",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "detailConstraint",
                        columnNames = {"schlId", "svyYr"}
                )
        }
)
@Entity
public class UniversityDetail {

    @Id
    @GeneratedValue
    @Column(name = "detail_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    University university;

    /**
     * getComparisonUniversitySearchList
     */
    @Column(name = "schlId", length = 15)
    private String schlId;      // 고유ID
    @Column(name = "svyYr", length = 10)
    private String svyYr;       // 공시년도
    @Column(length = 50)
    private String estbDivNm;   // 설립구분명 *
    @Column(length = 50)
    private String schlKndNm;   // 학교유형명

    /**
     * getComparisonFreshmanChanceBalanceSelectionRatio
     */
    @Column(length = 50)
    private String fcbsr;   //  신입생 기회균형 선발 비율 *

    /**
     * getNoticeFreshmanDrafteesRate
     */
    @Column(length = 50)
    private String freshRate;        // 정원내 신입생 충원율 *
    @Column(length = 50)
    private String recruits;    // 정원내 모집인원 *
    @Column(length = 50)
    private String selection;   // 정원내 입학자  *
    @Column(length = 50)
    private String freshAvg;    // 평균
    @Column(length = 50)
    private String freshImg;    // 랭크(별)

    /**
     * getNoticeEnrolledStudentDrafteesRate
     */
    @Column(length = 50)
    private String enroll;  // 학생정원
    @Column(length = 50)
    private String students;    // 재학생수 *
    @Column(length = 50)
    private String enrollRate;   // 재학생충원율
    @Column(length = 50)
    private String enrollAvg;   //  평균
    @Column(length = 50)
    private String enrollImg;   // 랭크(별)


    /**
     * getInsideFixedNumberFreshmanCompetitionRate
     */
    @Column(length = 50)
    private String compRate;    // 경쟁률  *

    /**
     * getNoticeGraduateEmploymentRate
     */
    @Column(length = 50)
    private String graduate;    // 졸업자
    @Column(length = 50)
    private String employed;    // 취업자
    @Column(length = 50)
    private String candidate;   // 취업대상자
    @Column(length = 50)
    private String employedRate;    // 취업률 *
    @Column(length = 50)
    private String employedAvg;     // 평균
    @Column(length = 50)
    private String employedImg;     // 랭크

    /**
     * getComparisonFullTimeFacultyForPersonStudentNumberEnrolledStudent
     */
    @Column(length = 50)
    private String stprRate;    //  전임교원 1인당 학생수 *      -> 삭제

    /**
     * getComparisonLectureChargeRatio
     */
    @Column(length = 50)
    private String lcRate;      // 전임교원 강의담당비율

    /**
     * getComparisonFullTimeFacultyInsideOfSchoolForPersonResearchGrant
     */
    @Column(length = 50)
    private String insideResearchGrant;     // 전임교원 교내 1인당 연구비

    /**
     * getComparisonFullTimeFacultyOutsideOfSchoolForPersonResearchGrant
     */
    @Column(length = 50)
    private String outsideResearchGrant;    // 전임교원 교외 1인당 연구비

    /**
     * getNoticeFullTimeFacultyResearchCrntSt
     */
    @Column(length = 50)
    private String professors;       // 전임교원
    @Column(length = 50)
    private String researchRate1;   // 한국연구재단등재지
    @Column(length = 50)
    private String researchRate2;   // 전임교원1인당 논문실적
    @Column(length = 50)
    private String researchAvg;     // 평균
    @Column(length = 50)
    private String researchImg;     // 랭크


    /**
     * getComparisonDormitoryAcceptanceCrntSt
     */
    @Column(length = 50)
    private String dormitoryRate;   // 기숙사 수용 현황  *

    /**
     * getComparisonLibraryBudgetCrntSt
     */
    @Column(length = 50)
    private String libraryBudget;   // 도서관예산 현황

    /**
     * getComparisonStudentForPersonDataPurchasePrice
     */
    @Column(length = 50)
    private String purchasePrice;   // 학생 1인당 자료 구입비

    /**
     * getComparisonTuitionCrntSt
     */
    @Column(length = 50)
    private String tuition;     // 등록금 현황 조회  *

    /**
     * getComparisonScholarshipBenefitCrntSt
     */
    @Column(length = 50)
    private String scholarship;     // 장학금 수혜 현황 조회 *

    /**
     * getComparisonEducationalExpensesReductionCrntSt
     */
    @Column(length = 50)
    private String expenseReduction;    // 학생 1인당 교육비 환원 현황

    /**
     * getComparisonEducationExpensesLoanCrntSt
     */
    @Column(length = 50)
    private String loan;        // 학자금 대출 현황

    /**
     * getComparisonEducationExpensesLoanUseStudentRatioTuition
     */
    @Column(length = 50)
    private String loanUseRate;     // 학자금대출 이용학생비율

    public UniversityDetail() {
    }

    public void setUniversity(University university) {
        this.university = university;
    }
    @Builder
    public UniversityDetail(String schlId, String svyYr, String estbDivNm, String schlKndNm, String fcbsr, String freshRate, String recruits, String selection, String freshAvg, String freshImg, String enroll, String students, String enrollRate, String enrollAvg, String enrollImg, String compRate, String graduate, String employed, String candidate, String employedRate, String employedAvg, String employedImg, String stprRate, String lcRate, String insideResearchGrant, String outsideResearchGrant, String professors, String researchRate1, String researchRate2, String researchAvg, String researchImg, String dormitoryRate, String libraryBudget, String purchasePrice, String tuition, String scholarship, String expenseReduction, String loan, String loanUseRate) {
        this.schlId = schlId;
        this.svyYr = svyYr;
        this.estbDivNm = estbDivNm;
        this.schlKndNm = schlKndNm;
        this.fcbsr = fcbsr;
        this.freshRate = freshRate;
        this.recruits = recruits;
        this.selection = selection;
        this.freshAvg = freshAvg;
        this.freshImg = freshImg;
        this.enroll = enroll;
        this.students = students;
        this.enrollRate = enrollRate;
        this.enrollAvg = enrollAvg;
        this.enrollImg = enrollImg;
        this.compRate = compRate;
        this.graduate = graduate;
        this.employed = employed;
        this.candidate = candidate;
        this.employedRate = employedRate;
        this.employedAvg = employedAvg;
        this.employedImg = employedImg;
        this.stprRate = stprRate;
        this.lcRate = lcRate;
        this.insideResearchGrant = insideResearchGrant;
        this.outsideResearchGrant = outsideResearchGrant;
        this.professors = professors;
        this.researchRate1 = researchRate1;
        this.researchRate2 = researchRate2;
        this.researchAvg = researchAvg;
        this.researchImg = researchImg;
        this.dormitoryRate = dormitoryRate;
        this.libraryBudget = libraryBudget;
        this.purchasePrice = purchasePrice;
        this.tuition = tuition;
        this.scholarship = scholarship;
        this.expenseReduction = expenseReduction;
        this.loan = loan;
        this.loanUseRate = loanUseRate;
    }
}
