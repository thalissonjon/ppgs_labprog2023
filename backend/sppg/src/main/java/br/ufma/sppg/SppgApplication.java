package br.ufma.sppg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SppgApplication implements WebMvcConfigurer {

	@Override
    public void addCorsMappings(CorsRegistry cors) {
        cors.addMapping("/**")
            .allowedMethods("GET", "PUT", "DELETE", "POST", "OPTIONS");
    }

	public static void main(String[] args) {
		SpringApplication.run(SppgApplication.class, args);	
	}

}
