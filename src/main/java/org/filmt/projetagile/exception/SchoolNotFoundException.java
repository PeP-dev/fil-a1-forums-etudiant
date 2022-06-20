package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "School Not Found")
public class SchoolNotFoundException extends PublicException {

    public SchoolNotFoundException(final String message) {
        super(message);
    }
}
