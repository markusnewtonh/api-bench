package com.markusnh.soap;

@org.springframework.stereotype.Service
public class Service {

  public String echo(final String requestData) {
    return requestData;
  }

  public String modify(final String requestData) {
    return new StringBuilder(requestData).reverse().toString();
  }
}
