package ch.clip.trips.repo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {
    /**
     * CORS - Policy - from known Servers
     */
    @Autowired
    Environment env;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("Allowed Origin: " + env.getProperty("allowedOrigin"));
        registry.addMapping("/**").allowedOrigins(env.getProperty("allowedOrigin"));
    }
}
