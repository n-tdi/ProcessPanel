package world.ntdi.processpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import world.ntdi.processpanel.system.ENVManager;
import world.ntdi.processpanel.system.parent.ENVReader;

import java.util.Collections;

@SpringBootApplication
public class ProcessPanelApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProcessPanelApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", ENVManager.getPort()));
        app.run(args);
        System.out.println(ENVManager.getKey() + " " + ENVManager.getService());
        System.out.println(ENVReader.getEnvMap());
    }

    @Configuration
    public class WebConfiguration implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedMethods("*");
        }
    }

}
