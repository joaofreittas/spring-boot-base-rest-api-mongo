package com.rest.baseapi.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {

  private static final long serialVersionUID = -5072064155255930047L;

  private List<String> validationMessages;
  private String jsonResponse;
  private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

  public ValidationException(List<String> validationMessages) {
    this.validationMessages = validationMessages;
  }

  public ValidationException(List<String> validationMessages, HttpStatus httpStatus) {
    this.validationMessages = validationMessages;
    this.httpStatus = httpStatus;
  }

  public ValidationException(String validationMessage, HttpStatus httpStatus) {
    this.validationMessages = new ArrayList<>();
    this.validationMessages.add(validationMessage);
    this.httpStatus = httpStatus;
  }

  public ValidationException(String validationMessage) {
    this.validationMessages = new ArrayList<>();
    this.validationMessages.add(validationMessage);
  }

  public ValidationException(String validationMessage, String jsonResponse) {
    this.validationMessages = new ArrayList<>();
    this.validationMessages.add(validationMessage);
    this.jsonResponse = jsonResponse;
  }

  public List<String> getValidationMessages() {
    return this.validationMessages;
  }

  public String getMessage() {
    return this.validationMessages.toString();
  }

  public String getJsonResponse() {
    return this.jsonResponse;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}
