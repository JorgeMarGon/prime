package com.prime.hotel.infrastructure.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.common.errors.RestApiErrorCode;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SearchErrorCode implements RestApiErrorCode {
  SEARCH_NOT_FOUND(1001, "This search has not been found");

  private final int value;
  private final String message;

  SearchErrorCode(int value, String message) {
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
