package world.ntdi.processpanel.system;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ENVManager {
    @Getter
    private final static String key;
    @Getter
    private final static String service;
    @Getter
    private final static String parentENV;
    @Getter
    private final static String port;

    static {
        Dotenv dotenv = Dotenv.load();
        key = dotenv.get("KEY");
        service = dotenv.get("SYSTEMD_SERVICE");
        parentENV = dotenv.get("PARENT_ENV");
        port = dotenv.get("PORT");

    }

    public boolean correctKey(String check) {
        return getKey().equals(check);
    }
}
