package com.siabe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.siabe.utils.PropiedadesArchivosGuardados;

@SpringBootApplication
@EnableConfigurationProperties({
	PropiedadesArchivosGuardados.class
})
public class SiabeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		//SpringApplication.run(SiabeApplication.class, args);
		 SpringApplicationBuilder builder = new SpringApplicationBuilder(SiabeApplication.class);
	     builder.headless(false).run(args);

	}
	
	// Override the configure method from the SpringBootServletInitializer class
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SiabeApplication.class);
    }
}
