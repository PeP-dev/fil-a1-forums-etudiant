package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Post Not Found")
public class PostNotFoundException extends PublicException {

    public PostNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public static PostNotFoundException genericById(String id) {
        return new PostNotFoundException(String.format("Couldn't find a post with a matching identifier : %s", id));
    }

}
