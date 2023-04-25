package hello.vuespring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class FilePath {

    private final String qsPath = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_combined_data.json";
    private final String qsPath_2021 = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_university_info_2021.json";
    private final String qsPath_2020 = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/qs/QS_university_info_2020.json";

    private final String thePath = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_combined_data.json";
    private final String thePath_2021 = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_university_info_2021.json";
    private final String thePath_2020 = "/Users/kimyuseong/study/vue-springV2/src/main/resources/jsondata/the/THE_university_info_2020.json";

    private final String folderPath = "/Users/kimyuseong/study/vue-springV2/src/test/resources/vue-spring data";


}
