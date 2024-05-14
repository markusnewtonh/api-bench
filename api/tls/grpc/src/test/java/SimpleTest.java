import io.grpc.*;
import proto.ServiceGrpc;
import proto.ServiceOuterClass;

import java.io.File;
import java.io.IOException;

public class SimpleTest {
  /** Start GrpcApplication before running test below */
  public static void main(String[] args) throws IOException {
    final ChannelCredentials tlsCred =
        TlsChannelCredentials.newBuilder()
            .trustManager(new File("src/main/resources/certs/ca-cert.pem"))
            .build();
    final ManagedChannel channel =
        Grpc.newChannelBuilderForAddress("localhost", 8080, tlsCred).build();

    final ServiceGrpc.ServiceBlockingStub testStub = ServiceGrpc.newBlockingStub(channel);

    final ServiceOuterClass.Product testProduct =
        ServiceOuterClass.Product.newBuilder()
            .setId(0)
            .setName("Table")
            .setDescription("Ordinary table.")
            .build();

    final ServiceOuterClass.ProductRequest testRequest =
        ServiceOuterClass.ProductRequest.newBuilder().setProduct(testProduct).build();

    final ServiceOuterClass.ProductResponse testEchoResponse = testStub.echo(testRequest);
    System.out.println("----- echo() test -----");
    System.out.println("id: " + testEchoResponse.getProduct().getId());
    System.out.println("name: " + testEchoResponse.getProduct().getName());
    System.out.println("description: " + testEchoResponse.getProduct().getDescription() + "\n");

    final ServiceOuterClass.ProductResponse testModifyResponse = testStub.modify(testRequest);
    System.out.println("----- modify() test -----");
    System.out.println("id: " + testModifyResponse.getProduct().getId());
    System.out.println("name: " + testModifyResponse.getProduct().getName());
    System.out.println("description: " + testModifyResponse.getProduct().getDescription());

    channel.shutdown();
  }
}
