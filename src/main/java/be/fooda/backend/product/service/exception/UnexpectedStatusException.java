package be.fooda.backend.product.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UnexpectedStatusException extends RuntimeException {

    public UnexpectedStatusException() {
        super("Unexpected HTTP Status..");
    }

    public UnexpectedStatusException(String message) {
        super(message);
    }
}
