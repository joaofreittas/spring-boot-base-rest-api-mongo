package com.rest.baseapi.crud;

public interface CrudMapper<ENTITY, DTO> {
  DTO toDto(ENTITY entity);
  ENTITY toModel(DTO dto);
}
