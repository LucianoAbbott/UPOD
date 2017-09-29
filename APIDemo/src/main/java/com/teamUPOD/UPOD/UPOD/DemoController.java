package com.teamUPOD.UPOD.UPOD;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class DemoController {

    @RequestMapping("/demo")
    public String demoResponse() {
    		
        return "Api call worked";
    }

}