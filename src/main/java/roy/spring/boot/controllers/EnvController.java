package roy.spring.boot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EnvController {

    @RequestMapping("/env")
    public Map<String, String> getEnvironmentInformation(){
        Map<String, String> env = System.getenv();
        return env;
    }

}
