package hello.vuespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
@SpringBootApplication
public class VueSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueSpringApplication.class, args);
	}

}
