package world.ntdi.processpanel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import world.ntdi.processpanel.system.CommandHelper;
import world.ntdi.processpanel.system.ENVManager;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/poweraction")
public class PowerActionController {

    @PostMapping("/start")
    public ResponseEntity<Map<String, String>> start(@RequestParam String key) {
        return doRequest(key, "start");
    }

    @PostMapping("/stop")
    public ResponseEntity<Map<String, String>> stop(@RequestParam String key) {
        return doRequest(key, "stop");
    }

    @PostMapping("/restart")
    public ResponseEntity<Map<String, String>> restart(@RequestParam String key) {
        return doRequest(key, "restart");
    }

    private ResponseEntity<Map<String, String>> doRequest(String key, String action) {
        if (ENVManager.correctKey(key)) {
            CommandHelper.systemdCommand(action);
            return ResponseEntity.ok(Map.of("response", "Success!"));
        }
        return ResponseEntity.badRequest().build();
    }
}
