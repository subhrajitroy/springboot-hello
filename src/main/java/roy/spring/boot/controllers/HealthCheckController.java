package roy.spring.boot.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import roy.spring.boot.main.HealthCheck;
import roy.spring.boot.main.RestartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

@RestController
public class HealthCheckController {

    @RequestMapping("/info")
    public Dictionary<String, String> getInfo(HttpServletRequest request){
        Hashtable<String, String> headers = new Hashtable<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            headers.put(headerName,request.getHeader(headerName));
        }
        return headers;
    }

    @RequestMapping(value = "/restart",method = RequestMethod.POST)
    public HealthCheck restart(@RequestBody RestartRequest restartRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Binding Exception");
        }
        String serviceName = restartRequest.getServiceName();
        HealthCheck healthCheck = new HealthCheck();
        if(new Random().nextBoolean()){
            healthCheck.addServiceCheck(serviceName,"ERROR");
            throw new RuntimeException("Exception");
        }
        healthCheck.addServiceCheck(serviceName,"OK");
        return healthCheck;
    }



}
