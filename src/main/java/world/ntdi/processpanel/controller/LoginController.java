package world.ntdi.processpanel.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import world.ntdi.processpanel.system.ENVManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final Bucket bucket;

    public LoginController() {
        Bandwidth limit = Bandwidth.classic(20, Refill.intervally(10, Duration.ofMinutes(10)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/checkkey")
    public ResponseEntity<Map<String, String>> checkKey(@RequestParam String key) {
        if (bucket.tryConsume(1)) {
            if (ENVManager.correctKey(key)) {
                return ResponseEntity.ok(Map.of("response", "correct"));
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build() ;
    }
}
