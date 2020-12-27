package com.rest.baseapi.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
  public String id;
  public String name;
  public BigDecimal price;
  public Integer quantity;
}
