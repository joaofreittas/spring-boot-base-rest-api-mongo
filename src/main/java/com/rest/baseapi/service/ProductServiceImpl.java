package com.rest.baseapi.service;

import com.rest.baseapi.crud.CrudServiceImpl;
import com.rest.baseapi.entity.Product;
import com.rest.baseapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProductServiceImpl extends CrudServiceImpl<Product> implements ProductService{

  @Autowired private ProductRepository repository;

  public ProductServiceImpl(ProductRepository repository) {
    super(repository);
  }
}
