package tn.com.well;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
public class WellApplication {

    public static void main(String[] args) {
        SpringApplication.run(WellApplication.class, args);
    }

}
