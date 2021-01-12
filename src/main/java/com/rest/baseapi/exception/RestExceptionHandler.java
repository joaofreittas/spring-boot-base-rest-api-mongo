package com.rest.baseapi.exception;

import com.rest.baseapi.crud.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request) {
    ApiResponse<Object> errors = getErrors(ex);
    return new ResponseEntity<>(errors, status);
  }

  private ApiResponse<Object> getErrors(MethodArgumentNotValidException ex) {
    List<String> erros = new ArrayList<String>();
    ApiResponse<Object> response = new ApiResponse<Object>();
    ex.getBindingResult().getFieldErrors().stream()
            .map(error -> erros.add(error.getDefaultMessage()))
            .collect(Collectors.toList());

    response.setValidations(erros);
//    log.warn(ex.getMessage());
    return response;
  }

}
