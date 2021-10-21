package nl.lnijzink.autogarage;

import nl.lnijzink.autogarage.storage.StorageProperties;
import nl.lnijzink.autogarage.storage.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
//@ComponentScan({"nl.lnijzink.autogarage", "controller"})
public class AutogarageApplication {

	public static void main(String[] args) {
//		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(AutogarageApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
