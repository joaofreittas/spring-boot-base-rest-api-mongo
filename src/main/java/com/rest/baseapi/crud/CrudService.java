package com.rest.baseapi.crud;

import com.rest.baseapi.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudService<ENTITY, DTO> {

  @Transactional(readOnly = true)
  List<ENTITY> getAll() throws ValidationException;

  @Transactional
  Page<ENTITY> getAll(PageRequest pageRequest);

  @Transactional
  Page<DTO> getAllAsPageableDto(PageRequest pageRequest);

  @Transactional
  Optional<DTO> find(String id);

  DTO save(DTO dto) throws ValidationException;

  void delete(DTO dto) throws ValidationException;

  void validSave(DTO dto) throws ValidationException;

  void validDelete(DTO dto) throws ValidationException;


}
