package com.rest.baseapi.controller;

import com.rest.baseapi.crud.CrudController;
import com.rest.baseapi.entity.Product;
import com.rest.baseapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends CrudController<Product> {

  private ProductService service;

  public ProductController(ProductService service) {
    super(service);
    this.service = service;
  }

}
