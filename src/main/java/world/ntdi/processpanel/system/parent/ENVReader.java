package world.ntdi.processpanel.system.parent;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import world.ntdi.processpanel.system.ENVManager;

import java.io.*;
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

    public void removeKey(String key) {
        envMap.remove(key);
    }

    public void uploadKeyValue(String key, String value) {
        envMap.put(key, value);
    }

    public void uploadValuesToFile() {
        if (ENVManager.getParentENV() != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(ENVManager.getParentENV(), false));
                for (Map.Entry<String, String> pair : getEnvMap().entrySet()) {
                    writer.write(pair.getKey() + "=" + pair.getValue() + "\n");
                }
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
