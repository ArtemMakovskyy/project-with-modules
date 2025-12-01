package com.store.controller;

import com.store.service.RedisOpsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisOpsService redisOpsService;

    @PostMapping("/put")
    public ResponseEntity<Void> put(@RequestParam String key, @RequestBody Map<String, Object> body,
                                    @RequestParam(defaultValue = "600") long ttlSeconds) {
        redisOpsService.put(key, body, Duration.ofSeconds(ttlSeconds));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<Object> get(@RequestParam String key) {
        Object value = redisOpsService.get(key);
        return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam String key) {
        redisOpsService.delete(key);
        return ResponseEntity.noContent().build();
    }
}
