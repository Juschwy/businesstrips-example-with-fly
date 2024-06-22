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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String allowedOrigin = System.getenv("CORS_ALLOWED_ORIGIN");
        if (allowedOrigin == null) {
            allowedOrigin = "http://localhost:3000";
        }
        System.out.println("Allowed Origin: " + allowedOrigin);
        registry.addMapping("/**").allowedOrigins(allowedOrigin);
    }
}
