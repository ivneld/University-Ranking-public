package hello.vuespring;

import hello.vuespring.V2.config.JdbcTemplateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import(JdbcTemplateConfig.class)
@SpringBootApplication(scanBasePackages = "hello.vuespring.V2.web")
public class VueSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueSpringApplication.class, args);
	}

}
