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
public class CrudServiceImpl<ENTITY, DTO> implements CrudService<ENTITY, DTO>{

  private final CrudMapper<ENTITY, DTO> mapper;
  private final MongoRepository<ENTITY, String> repository;

  public CrudServiceImpl(CrudMapper<ENTITY, DTO> mapper, MongoRepository<ENTITY, String> repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public List<ENTITY> getAll() {
    return repository.findAll();
  }

  @Override
  @Cacheable
  public Page<ENTITY> getAll(PageRequest pageRequest) {
    return repository.findAll(pageRequest);
  }

  @Override
  public Page<DTO> getAllAsPageableDto(PageRequest pageRequest) {
    Page<ENTITY> entitys = getAll(pageRequest);
    return entitys.map(e -> mapper.toDto(e));
  }

  @Override
  public Optional<DTO> find(String id) {
    return repository.findById(id).map(s -> Optional.of(mapper.toDto(s))).orElse(Optional.empty());
  }

  @Override
  @Transactional
  public DTO save(DTO dto) throws ValidationException {
    try {
      validSave(dto);
      ENTITY entity = repository.save(mapper.toModel(dto));
      return this.mapper.toDto(entity);
    } catch (ValidationException e) {
//      log.error(e.getMessage(), e);
      throw new ValidationException(e.getValidationMessages(), e.getHttpStatus());
    }
  }

  @Override
  public void delete(DTO dto) throws ValidationException {
    validDelete(dto);
    repository.delete(mapper.toModel(dto));
  }

  @Override
  public void validSave(DTO dto) throws ValidationException {}

  @Override
  public void validDelete(DTO dto) throws ValidationException {}

}
