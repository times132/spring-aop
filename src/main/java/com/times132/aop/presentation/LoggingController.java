package com.times132.aop.presentation;

import com.times132.aop.core.LogAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LoggingController {

    @LogAnnotation(collection = "request_log")
    @GetMapping()
    public String logHome() {
        return "logging home";
    }
}
