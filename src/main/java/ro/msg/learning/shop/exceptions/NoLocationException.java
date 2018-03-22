package ro.msg.learning.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND) //404
public class NoLocationException extends RuntimeException {

    public NoLocationException(int locationId) {

        super("No location found with: " + locationId);
    }
}
