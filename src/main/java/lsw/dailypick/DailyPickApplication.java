package lsw.dailypick;

import lsw.dailypick.config.DotenvApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DailyPickApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DailyPickApplication.class);
		app.addInitializers(new DotenvApplicationContextInitializer());
		app.run(args);	}

}
