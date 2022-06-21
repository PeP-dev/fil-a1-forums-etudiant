package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Reply Not Found")
public class ReplyNotFoundException extends PublicException {

    public ReplyNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public static ReplyNotFoundException genericById(String id) {
        return new ReplyNotFoundException(String.format("Couldn't find a reply with a matching identifier : %s", id));
    }

}
