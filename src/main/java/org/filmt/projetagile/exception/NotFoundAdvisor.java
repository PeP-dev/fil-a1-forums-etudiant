package org.filmt.projetagile.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PublicException.class)
    public ResponseEntity<Object> handlePublicException(PublicException ex, HttpServletRequest req, WebRequest webReq) {
        return handleExceptionInternal(ex, new ApiError(ex.getStatus(),ex.getLocalizedMessage(), req.getRequestURI()), new HttpHeaders(), ex.getStatus(), webReq);
    }
}
