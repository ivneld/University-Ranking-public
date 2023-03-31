package hello.vuespring.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MainPageDto {

    private List<TempDto> universities;
    private List<String> majors;

    public MainPageDto(List<TempDto> universities, List<String> majors) {
        this.universities = universities;
        this.majors = majors;
    }
}
