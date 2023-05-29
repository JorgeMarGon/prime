package com.prime.common.errors;

import java.io.Serial;
import java.io.Serializable;

public class RestApiErrorDetail implements Serializable {
  @Serial private static final long serialVersionUID = 327474327860217046L;
  private String key;
  private Serializable value;

  public RestApiErrorDetail(String key, Serializable invalidValue) {
    this.key = key;
    this.value = invalidValue;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Serializable getValue() {
    return value;
  }

  public void setValue(Serializable value) {
    this.value = value;
  }
}
