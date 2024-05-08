package com.markusnh.rest.service;

import com.markusnh.rest.model.Product;
import com.markusnh.rest.model.ProductRequest;

@org.springframework.stereotype.Service
public class Service {

  public Product echo(final ProductRequest request) {
    return new Product(request.id(), request.name(), request.description());
  }

  public Product modify(final ProductRequest request) {
    return new Product(
        request.id(),
        request.name(),
        modifyDescription(request.description()));
  }

  private String modifyDescription(final String requestDescription) {
    return new StringBuilder(requestDescription).reverse().toString();
  }
}
