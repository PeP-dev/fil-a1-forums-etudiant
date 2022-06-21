package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Category Not Found")
public class CategoryNotFoundException extends PublicException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public static CategoryNotFoundException genericById(String id) {
        return new CategoryNotFoundException(String.format("Couldn't find a category with a matching identifier : %s", id));
    }
}
