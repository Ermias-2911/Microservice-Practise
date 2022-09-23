package com.dailycodebuffer.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/customerServiceFallback")
    public String customerServiceFallBackMethod(){
        return "Customer Service is taking longer time than expected. " +
                       " Please Try again! ";
    }

    @GetMapping("/departmentServiceFallback")
    public String departmentServiceFallBackMethod(){
        return "Department Service is taking longer time than expected. " +
                " Please Try again! ";
    }
}
