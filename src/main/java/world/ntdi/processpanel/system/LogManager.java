package world.ntdi.processpanel.system;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class LogManager {
    @Getter
    private final LinkedList<String> output = new LinkedList<>();

    public void addLine(String line) {
        output.add(formatCurrentTimeStamp() + " " + line);
    }

    public void addLine(List<String> lines) {
        lines.forEach(LogManager::addLine);
    }

    private String formatCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }
}
