package ro.msg.learning.shop.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NoLocationException.class)
    @ResponseBody
    public String noLocationFoundException(NoLocationException exception) {

        return exception.getMessage();
    }

}
