package hello.vuespring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class ConnectionTest {

    @Test
    @Rollback(false)
    public void test() {

    }
}
