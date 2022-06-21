package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Group Not Found")
public class GroupNotFoundException extends PublicException {

    public GroupNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public static GroupNotFoundException genericById(String id) {
        return new GroupNotFoundException(String.format("Couldn't find a group with a matching identifier : %s", id));
    }
}
