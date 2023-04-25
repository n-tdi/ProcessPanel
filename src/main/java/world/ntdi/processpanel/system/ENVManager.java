package world.ntdi.processpanel.system;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class ENVManager {
    @Getter
    private final static String key;
    @Getter
    private final static String service;

    static {
        Map<String, String> envMap = System.getenv();
        if (envMap.containsKey("KEY")) {
            key = envMap.get("KEY");
        } else {
            throw new RuntimeException("No Key Specified in .env");
        }
        if (envMap.containsKey("SYSTEMD_SERVICE")) {
            service = envMap.get("SYSTEMD_SERVICE");
        } else {
            throw new RuntimeException("No SystemD Service Specified in .env");
        }
    }

    public boolean correctKey(String check) {
        return getKey().equals(check);
    }
}
