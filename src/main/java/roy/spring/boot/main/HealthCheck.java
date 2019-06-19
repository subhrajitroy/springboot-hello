package roy.spring.boot.main;

import java.util.HashMap;
import java.util.Map;

public class HealthCheck {

    private final HashMap<String, String> services;

    public HealthCheck(){
        services = new HashMap<>();
    }

    public void addServiceCheck(String serviceName,String status){
        services.put(serviceName,status);
    }

    public Map<String,String> getServiceStatus(){
        return services;
    }

}
