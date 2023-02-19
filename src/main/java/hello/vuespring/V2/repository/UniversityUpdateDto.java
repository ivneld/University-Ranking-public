package hello.vuespring.V2.repository;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class UniversityUpdateDto {
    private int totStud;
    private int tuition;
    private JSONObject source;

    public UniversityUpdateDto() {

    }

    public UniversityUpdateDto(int totStud, int tuition, JSONObject source) {
        this.totStud = totStud;
        this.tuition = tuition;
        this.source = source;
    }
}
