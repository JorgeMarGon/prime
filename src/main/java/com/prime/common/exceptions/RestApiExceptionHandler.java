package com.prime.common.exceptions;

import com.prime.common.errors.GeneralErrorCode;
import com.prime.common.errors.RestApiError;
import com.prime.common.errors.RestApiErrorDetail;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  Logger log = LoggerFactory.getLogger(RestApiExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    RestApiError restApiError = new RestApiError(GeneralErrorCode.INVALID_OBJECT);
    ex.getBindingResult().getFieldErrors().stream()
        .map(error -> new RestApiErrorDetail(error.getField(), error.getDefaultMessage()))
        .forEach(restApiError::addDetail);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restApiError);
  }

  @ExceptionHandler({RestApiException.class})
  public ResponseEntity<Object> handleAll(final RestApiException ex) {
    log.error("Rest Api Exception: {}", ex.getMessage());
    HttpStatus status = HttpStatus.resolve(ex.getError().getStatus().value());
    if (Objects.isNull(status)) status = HttpStatus.INTERNAL_SERVER_ERROR;
    return new ResponseEntity<>(ex.getError(), new HttpHeaders(), status);
  }
}
