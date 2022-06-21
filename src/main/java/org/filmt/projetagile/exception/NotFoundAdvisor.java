package org.filmt.projetagile.exception;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class NotFoundAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PublicException.class)
    public ResponseEntity<Object> handlePublicException(PublicException ex, HttpServletRequest req, WebRequest webReq) {
        return handleExceptionInternal(ex, new ApiError(ex.getStatus(),ex.getLocalizedMessage(), req.getRequestURI()), new HttpHeaders(), ex.getStatus(), webReq);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleTruc(AuthenticationException ex, HttpServletRequest req, WebRequest webReq) {
        return handleExceptionInternal(ex, new ApiError(HttpStatus.NOT_FOUND,ex.getLocalizedMessage(), req.getRequestURI()), new HttpHeaders(), HttpStatus.NOT_FOUND, webReq);
    }
}
