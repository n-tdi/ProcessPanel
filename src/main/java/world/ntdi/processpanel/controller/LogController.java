package world.ntdi.processpanel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import world.ntdi.processpanel.system.ENVManager;
import world.ntdi.processpanel.system.LogManager;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {

    @GetMapping("getlogs")
    public ResponseEntity<Map<String, List<String>>> getLogs(@RequestParam String key) {
        if (ENVManager.correctKey(key)) {
            return ResponseEntity.ok(Map.of("logs", LogManager.getOutput()));
        }
        return ResponseEntity.badRequest().build();
    }
}
