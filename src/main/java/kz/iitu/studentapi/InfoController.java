package kz.iitu.studentapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {
    @GetMapping("/api/mailebai-beksultan/info")
    public Map<String, String> info() {
        return Map.of(
            "student", "Mailebai Beksultan",
            "group", "IT2-2313",
            "studentId", "37691",
            "version", "1.0.1-37691"
        );
    }
}
