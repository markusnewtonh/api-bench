package com.markusnh.rest.controller;

import com.markusnh.rest.model.Product;
import com.markusnh.rest.model.ProductRequest;
import com.markusnh.rest.service.Service;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {
  private final Service service;

  public RestController(Service service) {
    this.service = service;
  }

  @PostMapping("/echo")
  public Product echo(@RequestBody ProductRequest productRequest) {
//    System.err.println("request received");
    return service.echo(productRequest);
  }

  @PostMapping("/modify")
  public Product modify(@RequestBody ProductRequest productRequest) {
    return service.modify(productRequest);
  }
}
