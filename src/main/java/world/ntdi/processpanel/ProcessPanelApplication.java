package world.ntdi.processpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import world.ntdi.processpanel.system.CommandHelper;
import world.ntdi.processpanel.system.ENVManager;
import world.ntdi.processpanel.system.LogManager;

@SpringBootApplication
public class ProcessPanelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessPanelApplication.class, args);
        System.out.println(ENVManager.getKey() + " " + ENVManager.getService());
        CommandHelper.runCommand("echo hello world");
        LogManager.getOutput().forEach(System.out::println);
    }
}
