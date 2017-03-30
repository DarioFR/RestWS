package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class TestApplication {

	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 
	 @RequestMapping("/minimumNStrides")
	 public String calculateMinimumStrides(){
		 
		 return "TEST";
	 }
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
	
}
