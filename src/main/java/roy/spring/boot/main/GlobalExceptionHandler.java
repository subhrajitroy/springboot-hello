package roy.spring.boot.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { RuntimeException.class })
    protected ResponseEntity<Object> handleException(
            RuntimeException ex, HttpServletRequest request) {
        System.out.println(String.format("In exception handler with message %s ", ex.getMessage()));
        String bodyOfResponse = "Error caught in main exception handler";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                    HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request !!";
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key  : parameterMap.keySet()){
            String[] values = parameterMap.get(key);

            StringBuilder builder = new StringBuilder();
            for (String value : values){
                builder.append(value).append("-");
            }
            System.out.println(String.format("%s : %s",key,builder.toString()));
        }
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}
