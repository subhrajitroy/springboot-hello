package roy.spring.boot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"roy.spring.boot.controllers"})
@ComponentScan(value = {"roy.spring.boot.main"})
public class Application {
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);

//        for (String name: applicationContext.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
    }
}
