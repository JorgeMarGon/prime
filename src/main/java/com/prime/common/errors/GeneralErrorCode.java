package com.prime.common.errors;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GeneralErrorCode implements RestApiErrorCode {
  INVALID_OBJECT(4010, "Object is invalid");

  private final int value;
  private final String message;

  GeneralErrorCode(int value, String message) {
    this.value = value;
    this.message = message;
  }

  public int getValue() {
    return this.value;
  }

  public String getMessage() {
    return this.message;
  }
}
