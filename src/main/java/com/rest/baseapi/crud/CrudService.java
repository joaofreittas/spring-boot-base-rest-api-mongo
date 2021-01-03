package com.rest.baseapi.crud;

import com.rest.baseapi.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudService<ENTITY> {

  @Transactional(readOnly = true)
  List<ENTITY> getAll() throws ValidationException;

  @Transactional
  Page<ENTITY> getAllPageable(PageRequest pageRequest);

  @Transactional
  Optional<ENTITY> find(String id);

  ENTITY save(ENTITY entity) throws ValidationException;

  void delete(ENTITY entity) throws ValidationException;

  void validSave(ENTITY entity) throws ValidationException;

  void validDelete(ENTITY entity) throws ValidationException;


}
