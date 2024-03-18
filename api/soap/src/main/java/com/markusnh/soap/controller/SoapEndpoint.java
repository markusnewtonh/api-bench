package com.markusnh.soap.controller;

import com.markusnh.soap.model.Product;
import com.markusnh.soap.model.ProductRequest;
import com.markusnh.soap.service.Service;
import https.markusnewtonh_com.api_bench.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SoapEndpoint {
  private static final String NAMESPACE_URI = "https://markusnewtonh.com/api-bench";
  private final Service service;

  @Autowired
  public SoapEndpoint(Service service) {
    this.service = service;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEchoRequest")
  @ResponsePayload
  public GetEchoResponse getEcho(@RequestPayload GetEchoRequest request) {
    final ProductRequest productRequest =
        new ProductRequest(
            request.getProduct().getId(),
            request.getProduct().getName(),
            request.getProduct().getDescription());

    final Product product = service.echo(productRequest);

    final GetEchoResponse response = new GetEchoResponse();
    response.setProduct(convertToSoapProduct(product));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTypicalRequest")
  @ResponsePayload
  public GetModifyResponse getModify(@RequestPayload GetModifyRequest request) {
    final ProductRequest productRequest =
        new ProductRequest(
            request.getProduct().getId(),
            request.getProduct().getName(),
            request.getProduct().getDescription());

    final Product product = service.modify(productRequest);

    final GetModifyResponse response = new GetModifyResponse();
    response.setProduct(convertToSoapProduct(product));
    return response;
  }

  private https.markusnewtonh_com.api_bench.Product convertToSoapProduct(final Product product) {
    final https.markusnewtonh_com.api_bench.Product soapProduct =
        new https.markusnewtonh_com.api_bench.Product();
    soapProduct.setId(product.id());
    soapProduct.setName(product.name());
    soapProduct.setDescription(product.description());
    return soapProduct;
  }
}
