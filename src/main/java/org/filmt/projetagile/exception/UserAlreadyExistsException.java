package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyExistsException extends PublicException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
