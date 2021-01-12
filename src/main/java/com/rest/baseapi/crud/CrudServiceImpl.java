package com.rest.baseapi.crud;

import com.rest.baseapi.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
public class CrudServiceImpl<ENTITY> implements CrudService<ENTITY>{

  private final MongoRepository<ENTITY, String> repository;

  public CrudServiceImpl(MongoRepository<ENTITY, String> repository) {
    this.repository = repository;
  }

  @Override
  public List<ENTITY> getAll() {
    return repository.findAll();
  }

  @Override
  @Cacheable
  public Page<ENTITY> getAllPageable(PageRequest pageRequest) {
    return repository.findAll(pageRequest);
  }

  @Override
  public Optional<ENTITY> find(String id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public ENTITY save(ENTITY entity) throws ValidationException {
    try {
      validSave(entity);
      return repository.save(entity);
    } catch (ValidationException e) {
//      log.error(e.getMessage(), e);
      throw new ValidationException(e.getValidationMessages(), e.getHttpStatus());
    }
  }

  @Override
  public void delete(ENTITY entity) throws ValidationException {
    validDelete(entity);
    repository.delete(entity);
  }

  @Override
  public void validSave(ENTITY entity) throws ValidationException {}

  @Override
  public void validDelete(ENTITY entity) throws ValidationException {}

}
