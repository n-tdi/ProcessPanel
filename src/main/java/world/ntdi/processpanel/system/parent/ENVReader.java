package world.ntdi.processpanel.system.parent;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import world.ntdi.processpanel.system.ENVManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ENVReader {
    @Getter
    private final Map<String, String> envMap = new HashMap<>();

    static {
        String line;
        if (ENVManager.getParentENV() != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ENVManager.getParentENV()))) {
                while ((line = reader.readLine()) != null) {
                    String[] keyValuePair = line.split("=", 2);
                    if (keyValuePair.length > 1) {
                        String key = keyValuePair[0];
                        String value = keyValuePair[1];
                        envMap.put(key, value);
                    } else {
                        System.out.println("No Key=Value found in line, ignoring: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
