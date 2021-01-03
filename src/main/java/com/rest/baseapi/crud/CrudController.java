package com.rest.baseapi.crud;

import com.rest.baseapi.exception.ValidationException;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class CrudController<ENTITY> {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(CrudController.class);
  private CrudService<ENTITY> service;

  public CrudController(CrudService<ENTITY> service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<ENTITY>>> list(
          @RequestParam(
                  value = "page",
                  defaultValue = PaginationConstants.DEFAULT_START_PAGE,
                  required = false)
                  int page,
          @RequestParam(
                  value = "size",
                  defaultValue = PaginationConstants.DEFAULT_PAGE_OFFSET,
                  required = false)
                  int size,
          @RequestParam(
                  value = "ord",
                  defaultValue = PaginationConstants.DEFAULT_ORDER_COLUMN,
                  required = false)
                  String ord,
          @RequestParam(
                  value = "dir",
                  defaultValue = PaginationConstants.DEFAULT_SORT_DIRECTION,
                  required = false)
                  String dir) {
    try {
      ApiResponse<Page<ENTITY>> response = new ApiResponse<>();
      PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(dir), ord);
      Page<ENTITY> entities = service.getAllPageable(pageRequest);
      response.setResult(entities);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ApiResponse<ENTITY>> getById(@PathVariable(value = "id") String id) {
    Optional<ENTITY> ENTITY = service.find(id);
    if (ENTITY.isPresent()) {
      ApiResponse<ENTITY> response = new ApiResponse<>();
      response.setResult(ENTITY.get());
      return ResponseEntity.ok(response);
    } else {
      log.warn("Not found with id = {}", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<ApiResponse<ENTITY>> create(@RequestBody ENTITY ENTITY) {
    ApiResponse<ENTITY> response = new ApiResponse<>();
    try {
      response.setResult(service.save(ENTITY));
      return ResponseEntity.ok(response);
    } catch (ValidationException e) {
      log.error(e.getMessage(), e);
      response.setValidations(e.getValidationMessages());
      return new ResponseEntity<>(response, e.getHttpStatus());
    }
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ApiResponse<ENTITY>> update(
          @PathVariable(value = "id") String id, @RequestBody ENTITY newENTITY) {
    ApiResponse<ENTITY> response = new ApiResponse<>();
    try {
      Optional<ENTITY> optional = service.find(id);
      if (optional.isPresent()) {
        response.setResult(service.save(newENTITY));
        return ResponseEntity.ok(response);
      } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (ValidationException e) {
      log.error(e.getMessage(), e);
      response.setValidations(e.getValidationMessages());
      return new ResponseEntity<>(response, e.getHttpStatus());
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ApiResponse<Object>> remove(@PathVariable(value = "id") String id) {
    try {
      Optional<ENTITY> optional = service.find(id);
      if (optional.isPresent()) {
        service.delete(optional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (ValidationException e) {
      log.error(e.getMessage(), e);
      ApiResponse<Object> response = new ApiResponse<Object>();
      response.setValidations(e.getValidationMessages());
      return new ResponseEntity<ApiResponse<Object>>(response, e.getHttpStatus());
    }
  }

}
