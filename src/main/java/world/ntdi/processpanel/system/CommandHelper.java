package world.ntdi.processpanel.system;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class CommandHelper {

    public void systemdCommand(String action) {
        runSudoCommand("systemctl " + action + " " + ENVManager.getService());
    }

    public void runSudoCommand(String command) {
        runCommand("sudo " + command);
    }

    public void runCommand(String command) {
        LogManager.addCommandLine(command);
        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                LogManager.addLine(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
