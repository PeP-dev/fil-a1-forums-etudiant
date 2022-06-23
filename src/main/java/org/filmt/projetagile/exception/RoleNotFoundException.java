package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends PublicException {

    public RoleNotFoundException(final String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
