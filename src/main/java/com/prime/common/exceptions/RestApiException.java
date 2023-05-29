package com.prime.common.exceptions;

import com.prime.common.errors.RestApiError;
import com.prime.common.errors.RestApiErrorCode;
import java.io.Serial;
import org.springframework.http.HttpStatus;

public class RestApiException extends RuntimeException {

  @Serial private static final long serialVersionUID = -1335991811592142594L;

  protected final RestApiError error;

  public RestApiException(RestApiErrorCode code, HttpStatus status) {
    super(String.format("Code: %s", code.getMessage()));
    error = new RestApiError(status, code);
  }

  public RestApiError getError() {
    return error;
  }
}
