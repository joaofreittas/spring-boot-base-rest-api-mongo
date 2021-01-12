package com.rest.baseapi.crud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApiResponse<T> {

  public T result;
  public List<String> validations;

  public ApiResponse() {}

  public ApiResponse(T obj, List<String> validations) {
    this.validations = validations;
    this.result = obj;
  }

  public ApiResponse(T obj) {
    this.validations = null;
    this.result = obj;
  }

  public ApiResponse(T obj, String errorMessage) {
    ArrayList<String> validations = new ArrayList<>();
    validations.add(errorMessage);
    this.validations = validations;
    this.result = obj;
  }

  public List<String> getValidations() {
    if (this.validations == null) {
      this.validations = new ArrayList<>();
    }
    return validations;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public void setValidations(List<String> validations) {
    this.validations = validations;
  }
}
