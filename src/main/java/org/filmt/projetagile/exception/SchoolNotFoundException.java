package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SchoolNotFoundException extends PublicException {

    public SchoolNotFoundException(final String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public static SchoolNotFoundException genericById(String id) {
        return new SchoolNotFoundException(String.format("Couldn't find a school with matching id : %s", id));
    }
}
