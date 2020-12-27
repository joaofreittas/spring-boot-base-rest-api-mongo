package com.rest.baseapi.service;

import com.rest.baseapi.crud.CrudMapper;
import com.rest.baseapi.crud.CrudServiceImpl;
import com.rest.baseapi.dto.ProductDto;
import com.rest.baseapi.entity.Product;
import com.rest.baseapi.mapper.ProductMapper;
import com.rest.baseapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
class ProductServiceImpl extends CrudServiceImpl<Product, ProductDto> implements ProductService{

  @Autowired private ProductRepository repository;
  @Autowired private ProductMapper mapper;

  public ProductServiceImpl(ProductMapper mapper, ProductRepository repository) {
    super(mapper, repository);
  }
}
