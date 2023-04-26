package world.ntdi.processpanel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import world.ntdi.processpanel.system.ENVManager;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @GetMapping("/checkkey")
    public ResponseEntity<Map<String, String>> checkKey(@RequestParam String key) {
        if (ENVManager.correctKey(key)) {
            return ResponseEntity.ok(Map.of("response", "correct"));
        }
        return ResponseEntity.badRequest().build();
    }
}
