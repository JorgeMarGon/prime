package com.prime.common.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiError implements Serializable {
  @Serial private static final long serialVersionUID = -2278843985681971729L;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  private Instant timestamp;

  @JsonIgnore private transient HttpStatus status;
  @JsonIgnore private transient RestApiErrorCode code;
  private String message;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<RestApiErrorDetail> errorsDetails;

  public RestApiError(RestApiErrorCode code) {
    this(null, code);
  }

  public RestApiError(HttpStatus status, RestApiErrorCode code) {
    errorsDetails = new ArrayList<>();
    timestamp = Instant.now();
    this.status = status;
    this.message = code.getMessage();
  }

  public void addDetail(RestApiErrorDetail detail) {
    errorsDetails.add(detail);
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  @JsonIgnore
  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  @JsonIgnore
  public RestApiErrorCode getCode() {
    return code;
  }

  public void setCode(RestApiErrorCode code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public List<RestApiErrorDetail> getErrorsDetails() {
    return errorsDetails;
  }

  public void setErrorsDetails(List<RestApiErrorDetail> errorsDetails) {
    this.errorsDetails = errorsDetails;
  }
}
