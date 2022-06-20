package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Group Not Found")
public class GroupNotFoundException extends PublicException {

    public GroupNotFoundException(String message) {
        super(message);
    }
}
