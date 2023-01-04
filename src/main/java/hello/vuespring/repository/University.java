package hello.vuespring.repository;

import lombok.Data;
import org.json.simple.JSONObject;
import java.util.ArrayList;

@Data
public class University {

    private int id;
    private String name;
    private String engName;
    private String citation;
    private String compRate;
    private String intro;
    private String SFRatio;
    private int TotStud;
    private int tuition;
    private String website;

    private ArrayList<String> qs_subjects = new ArrayList<>();

    private ArrayList<String> the_subjects = new ArrayList<>();

    private JSONObject source;

    public University() {
    }

    public University(int id, String name, String engName, String citation, String compRate, String intro, String SFRatio,
                      int tot_stud, int tuition, String website, JSONObject source) {
        this.id = id;
        this.name = name;
        this.engName = engName;
        this.citation = citation;
        this.compRate = compRate;
        this.intro = intro;
        this.SFRatio = SFRatio;
        this.TotStud = tot_stud;
        this.tuition = tuition;
        this.website = website;
        this.source = source;
    }

    public void insertQSList(String source){
        this.qs_subjects.add(source);
    }

    public void insertTHEList(String source){
        this.the_subjects.add(source);
    }
}
