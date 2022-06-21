package org.filmt.projetagile.exception;

import org.springframework.http.HttpStatus;

public abstract class PublicException extends RuntimeException{
    public PublicException(String message) {
        super(message);
    }
    public abstract HttpStatus getStatus();
}
