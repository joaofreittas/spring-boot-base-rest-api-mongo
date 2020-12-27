package com.rest.baseapi.service;

import com.rest.baseapi.crud.CrudService;
import com.rest.baseapi.dto.ProductDto;
import com.rest.baseapi.entity.Product;

public interface ProductService extends CrudService<Product, ProductDto> {
}
