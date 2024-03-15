package controller;

import io.grpc.stub.StreamObserver;
import model.Product;
import model.ProductRequest;
import proto.ServiceGrpc;
import proto.ServiceOuterClass;
import service.Service;

public class ServiceImpl extends ServiceGrpc.ServiceImplBase {
  private static final Service service = new Service();

  @Override
  public void echo(ServiceOuterClass.ProductRequest request, StreamObserver<ServiceOuterClass.ProductResponse> responseObserver) {
    final ProductRequest productRequest =
        new ProductRequest(
            request.getProduct().getId(),
            request.getProduct().getName(),
            request.getProduct().getDescription());

    final Product product = service.echo(productRequest);

    final ServiceOuterClass.Product grpcProduct = convertToGrpcProduct(product);
    final ServiceOuterClass.ProductResponse response = ServiceOuterClass.ProductResponse.newBuilder().setProduct(grpcProduct).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void modify(ServiceOuterClass.ProductRequest request, StreamObserver<ServiceOuterClass.ProductResponse> responseObserver) {
    final ProductRequest productRequest =
        new ProductRequest(
            request.getProduct().getId(),
            request.getProduct().getName(),
            request.getProduct().getDescription());

    final Product product = service.modify(productRequest);

    final ServiceOuterClass.Product grpcProduct = convertToGrpcProduct(product);
    final ServiceOuterClass.ProductResponse response = ServiceOuterClass.ProductResponse.newBuilder().setProduct(grpcProduct).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private ServiceOuterClass.Product convertToGrpcProduct(Product product) {
    return ServiceOuterClass.Product.newBuilder()
        .setId(product.id())
        .setName(product.name())
        .setDescription(product.description())
        .build();
  }
}
