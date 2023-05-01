package hello.vuespring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@SpringBootTest
public class ResourceLoaderTest {

    ClassPathResource resource = new ClassPathResource("jsondata/QS_combined_data.json");

    @Test
    public void test() {
        System.out.println(resource.getPath());
    }
}
