package com.scaffold.api.diagnosis;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnostic")
public class DiagnosticController {

    private static final List<String> DIAGNOSTIC_LINKS = ImmutableList.of(
        "/diagnostic/status/heartbeat",
        "/diagnostic/status/diagnosis",
        "/diagnostic/status/nagios",
        "/diagnostic/version"
    );

    private final DatabaseHealthCheck healthCheck;
    private final String appVersion;

    @Autowired
    public DiagnosticController(DatabaseHealthCheck healthCheck, @Value("${APP_VERSION:UNKNOWN}") String appVersion) {
        this.healthCheck = healthCheck;
        this.appVersion = appVersion;
    }

    @GetMapping("/")
    public Map<String, List<String>> index() {
        Map<String, List<String>> content = Maps.newHashMap();
        content.put("endpoints", DIAGNOSTIC_LINKS);
        return content;
    }

    @GetMapping("/status/heartbeat")
    public String heartbeat() {
        return "OK";
    }

    @GetMapping("/status/diagnosis")
    public String diagnosis() {
        return "OK";
    }

    @GetMapping("/status/nagios")
    public ResponseEntity<String> nagios() {
        return this.healthCheck.isHealthy() ?
            ResponseEntity.ok("OK") :
            ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Not OK");
    }

    @GetMapping("/version")
    public String version() {
        return appVersion;
    }
}
