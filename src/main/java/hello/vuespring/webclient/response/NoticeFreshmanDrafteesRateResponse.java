package hello.vuespring.webclient.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeFreshmanDrafteesRateResponse {
    private String indctAvg;
    private String indctId;
    private String indctImg;
    private String indctVal1;
    private String indctVal2;
    private String indctVal3;
    private String indctVal4;
    private String schlId;
    private String schlKrnNm;
    private String svyYr;

    public NoticeFreshmanDrafteesRateResponse(String indctAvg, String indctId, String indctImg, String indctVal1, String indctVal2, String indctVal3, String indctVal4, String schlId, String schlKrnNm, String svyYr) {
        this.indctAvg = indctAvg;
        this.indctId = indctId;
        this.indctImg = indctImg;
        this.indctVal1 = indctVal1;
        this.indctVal2 = indctVal2;
        this.indctVal3 = indctVal3;
        this.indctVal4 = indctVal4;
        this.schlId = schlId;
        this.schlKrnNm = schlKrnNm;
        this.svyYr = svyYr;
    }
}
