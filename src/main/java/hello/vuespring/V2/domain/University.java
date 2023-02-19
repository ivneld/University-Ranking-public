package hello.vuespring.V2.domain;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class University {

    private Long id;
    private String name;
    private String engName;
    private String citation;
    private String compRate;
    private String intro;
    private String sfRatio;
    private Integer totStud;
    private Integer tuition;
    private String website;
    private JSONObject source;


    public University() {

    }

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

    public University(Long id, String name, String engName, String citation, String compRate, String intro, String sfRatio, Integer totStud, Integer tuition, String website) {
        this.id = id;
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
}
