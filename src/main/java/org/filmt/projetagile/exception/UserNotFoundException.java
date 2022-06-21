package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends PublicException {

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
