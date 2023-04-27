package world.ntdi.processpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import world.ntdi.processpanel.system.ENVManager;

import java.util.Collections;

@SpringBootApplication
public class ProcessPanelApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProcessPanelApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", ENVManager.getPort()));
        app.run(args);
        System.out.println(ENVManager.getKey() + " " + ENVManager.getService());
    }
}
