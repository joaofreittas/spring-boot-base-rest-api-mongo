package com.rest.baseapi.mapper;

import com.rest.baseapi.crud.CrudMapper;
import com.rest.baseapi.dto.ProductDto;
import com.rest.baseapi.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends CrudMapper<Product, ProductDto> {}
