package com.markusnh.soap;

import https.markusnewtonh_com.api_bench.GetEchoRequest;
import https.markusnewtonh_com.api_bench.GetEchoResponse;
import https.markusnewtonh_com.api_bench.GetModifyRequest;
import https.markusnewtonh_com.api_bench.GetModifyResponse;
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
    final GetEchoResponse response = new GetEchoResponse();
    response.setData(service.echo(request.getData()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTypicalRequest")
  @ResponsePayload
  public GetModifyResponse getModify(@RequestPayload GetModifyRequest request) {
    final GetModifyResponse response = new GetModifyResponse();
    response.setData(service.modify(request.getData()));
    return response;
  }
}
