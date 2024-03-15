import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.ServiceGrpc;
import proto.ServiceOuterClass;

public class SimpleTest {
  /**
   * Start GrpcApplication before running test below
   */
  public static void main(String[] args){
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8089).usePlaintext().build();

    ServiceGrpc.ServiceBlockingStub testStub = ServiceGrpc.newBlockingStub(channel);

    final ServiceOuterClass.Product testProduct =
        ServiceOuterClass.Product.newBuilder()
            .setId(0)
            .setName("Table")
            .setDescription("Ordinary table.").build();

    final ServiceOuterClass.ProductRequest testRequest = ServiceOuterClass.ProductRequest.newBuilder().setProduct(testProduct).build();

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
