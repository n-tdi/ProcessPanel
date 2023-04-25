package world.ntdi.processpanel.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ntdi.processpanel.system.ENVManager;
import world.ntdi.processpanel.system.LogManager;

import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {
    private final Bucket bucket;

    public LogController() {
        Bandwidth limit = Bandwidth.classic(20, Refill.intervally(10, Duration.ofMinutes(10)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("getlogs")
    public ResponseEntity<Map<String, List<String>>> getLogs(String key) {
        if (bucket.tryConsume(1)) {
            if (ENVManager.correctKey(key)) {
                return ResponseEntity.ok(Map.of("logs", LogManager.getOutput()));
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
