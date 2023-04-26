package world.ntdi.processpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import world.ntdi.processpanel.system.ENVManager;
import world.ntdi.processpanel.system.parent.ENVReader;

@SpringBootApplication
public class ProcessPanelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessPanelApplication.class, args);
        System.out.println(ENVManager.getKey() + " " + ENVManager.getService());
        System.out.println(ENVReader.getEnvMap());
    }
}
