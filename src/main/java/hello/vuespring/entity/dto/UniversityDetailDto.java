package hello.vuespring.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class UniversityDetailDto {
    private String schlKrnNm;
    private String schlId;      // 고유ID
    private String svyYr;       // 공시년도
    private String estbDivNm;   // 설립구분명 *
    private String schlKndNm;   // 학교유형명
    /**
     * getComparisonFreshmanChanceBalanceSelectionRatio
     */
    private String fcbsr;   //  신입생 기회균형 선발 비율 *

    /**
     * getNoticeFreshmanDrafteesRate
     */
    private String freshRate;        // 정원내 신입생 충원율 *
    private String recruits;    // 정원내 모집인원 *
    private String selection;   // 정원내 입학자  *
    private String freshAvg;    // 평균
    private String freshImg;    // 랭크(별)

    /**
     * getNoticeEnrolledStudentDrafteesRate
     */
    private String enroll;  // 학생정원
    private String students;    // 재학생수 *
    private String enrollRate;   // 재학생충원율
    private String enrollAvg;   //  평균
    private String enrollImg;   // 랭크(별)


    /**
     * getComparisonInsideFixedNumberFreshmanCompetitionRate
     */
    private String compRate;    // 경쟁률  *

    /**
     * getNoticeGraduateEmploymentRate
     */
    private String graduate;    // 졸업자
    private String employed;    // 취업자
    private String candidate;   // 취업대상자
    private String employedRate;    // 취업률 *
    private String employedAvg;     // 평균
    private String employedImg;     // 랭크

    /**
     * getComparisonFullTimeFacultyForPersonStudentNumberEnrolledStudent
     */
    private String stprRate;    //  전임교원 1인당 학생수 *      -> 삭제

    /**
     * getComparisonLectureChargeRatio
     */
    private String lcRate;      // 전임교원 강의담당비율

    /**
     * getComparisonFullTimeFacultyInsideOfSchoolForPersonResearchGrant
     */
    private String insideResearchGrant;     // 전임교원 교내 1인당 연구비

    /**
     * getComparisonFullTimeFacultyOutsideOfSchoolForPersonResearchGrant
     */
    private String outsideResearchGrant;    // 전임교원 교외 1인당 연구비

    /**
     * getNoticeFullTimeFacultyResearchCrntSt
     */
    private String professors;       // 전임교원
    private String researchRate1;   // 한국연구재단등재지
    private String researchRate2;   // 전임교원1인당 논문실적
    private String researchAvg;     // 평균
    private String researchImg;     // 랭크


    /**
     * getComparisonDormitoryAcceptanceCrntSt
     */
    private String dormitoryRate;   // 기숙사 수용 현황  *

    /**
     * getComparisonLibraryBudgetCrntSt
     */
    private String libraryBudget;   // 도서관예산 현황

    /**
     * getComparisonStudentForPersonDataPurchasePrice
     */
    private String purchasePrice;   // 학생 1인당 자료 구입비

    /**
     * getComparisonTuitionCrntSt
     */
    private String tuition;     // 등록금 현황 조회  *

    /**
     * getComparisonScholarshipBenefitCrntSt
     */
    private String scholarship;     // 장학금 수혜 현황 조회 *

    /**
     * getComparisonEducationalExpensesReductionCrntSt
     */
    private String expenseReduction;    // 학생 1인당 교육비 환원 현황

    /**
     * getComparisonEducationExpensesLoanCrntSt
     */
    private String loan;        // 학자금 대출 현황

    /**
     * getComparisonEducationExpensesLoanUseStudentRatioTuition
     */
    private String loanUseRate;     // 학자금대출 이용학생비율
}
