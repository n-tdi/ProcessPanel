package world.ntdi.processpanel.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import world.ntdi.processpanel.system.CommandHelper;
import world.ntdi.processpanel.system.ENVManager;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/poweraction")
public class PowerActionController {
    private final Bucket bucket;

    public PowerActionController() {
        Bandwidth limit = Bandwidth.classic(2, Refill.intervally(10, Duration.ofMinutes(10)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> start(@RequestParam String key) {
        return doRequest(key, "start");
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> stop(@RequestParam String key) {
        return doRequest(key, "stop");
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> restart(@RequestParam String key) {
        return doRequest(key, "restart");
    }

    private ResponseEntity<Map<String, String>> doRequest(String key, String action) {
        if (bucket.tryConsume(1)) {
            if (ENVManager.correctKey(key)) {
                CommandHelper.systemdCommand(action);
                return ResponseEntity.ok(Map.of("response", "Success!"));
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
