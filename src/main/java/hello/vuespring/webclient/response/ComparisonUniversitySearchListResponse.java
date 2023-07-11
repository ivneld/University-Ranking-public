package hello.vuespring.webclient.response;

import lombok.Data;

@Data
public class ComparisonUniversitySearchListResponse {
    private String clgcpDivCd;
    private String clgcpDivNm;
    private String estbDivCd;
    private String estbDivNm;
    private String schlDivCd;
    private String schlDivNm;
    private String schlFullNm;
    private String schlId;
    private String schlKndCd;
    private String schlKndNm;
    private String schlKrnNm;
    private String svyYr;
    private String znCd;
    private String znNm;

    public ComparisonUniversitySearchListResponse() {
    }

    public ComparisonUniversitySearchListResponse(String clgcpDivCd, String clgcpDivNm, String estbDivCd, String estbDivNm, String schlDivCd, String schlDivNm, String schlFullNm, String schlId, String schlKndCd, String schlKndNm, String schlKrnNm, String svyYr, String znCd, String znNm) {
        this.clgcpDivCd = clgcpDivCd;
        this.clgcpDivNm = clgcpDivNm;
        this.estbDivCd = estbDivCd;
        this.estbDivNm = estbDivNm;
        this.schlDivCd = schlDivCd;
        this.schlDivNm = schlDivNm;
        this.schlFullNm = schlFullNm;
        this.schlId = schlId;
        this.schlKndCd = schlKndCd;
        this.schlKndNm = schlKndNm;
        this.schlKrnNm = schlKrnNm;
        this.svyYr = svyYr;
        this.znCd = znCd;
        this.znNm = znNm;
    }
}
