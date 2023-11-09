package com.izzaz.wcc.javachallenge.util.exceptionhandling;

import com.izzaz.wcc.javachallenge.model.response.apiresponse.FieldError;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var response = ResponseUtil.generateBadRequestErrorResponse(FieldError.builder()
                .fieldError(ex.getFieldError().getField())
                .build());
        return buildResponseEntity(response);
    }

    private ResponseEntity<Object> buildResponseEntity(ResponseModel<Status, ?> response) {
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus().getCode()));
    }

    @ExceptionHandler({NotFound.class,})
    protected ResponseEntity<Object> handleNotFoundError(
            Exception ex) {
        var response = ResponseUtil.generateNotFoundErrorResponse(null);
        log.error(ex.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<Object> handleBadRequestError(
            Exception ex) {
        var response = ResponseUtil.generateBadRequestErrorResponse(null);
        log.error(ex.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<Object> handleInternalServerError(
            Exception ex) {
        var response = ResponseUtil.generateInternalServerErrorResponse(null);
        log.error(ex.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleUnauthorizedError(
            Exception ex) {
        var response = ResponseUtil.generateUnauthorizedErrorResponse(null);
        log.error(ex.getMessage());
        return buildResponseEntity(response);
    }


}
