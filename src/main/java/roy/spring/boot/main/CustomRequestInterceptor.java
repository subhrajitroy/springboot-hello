package roy.spring.boot.main;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {
        System.out.println("In pre handle");
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        System.out.println("After completion");
        if(ex != null){
            System.out.println("There was error ");
//            ex.printStackTrace();
        }
    }
}
