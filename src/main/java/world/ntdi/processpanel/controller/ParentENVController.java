package world.ntdi.processpanel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.ntdi.processpanel.object.KeyValue;
import world.ntdi.processpanel.system.ENVManager;
import world.ntdi.processpanel.system.parent.ENVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/penv")
public class ParentENVController {
    @GetMapping("/has")
    public ResponseEntity<Map.Entry<String, String>> hasPENV() {
        return ResponseEntity.ok(Map.entry("response", String.valueOf((ENVManager.getParentENV() != null))));
    }

    @GetMapping("/get")
    public ResponseEntity<Map.Entry<String, List<KeyValue<String, String>>>> getAllPENV(@RequestParam String key) {
        List<KeyValue<String, String>> values = new ArrayList<>();
        ENVReader.getEnvMap().forEach((key1, value) -> values.add(new KeyValue<>(key1, value)));

        if (ENVManager.correctKey(key)) {
            return ResponseEntity.ok(Map.entry("response",
                    values
            ));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Map.Entry<String, String>> updatePENV(@RequestParam String key, @RequestParam String key1, @RequestParam String value) {

    }
}
