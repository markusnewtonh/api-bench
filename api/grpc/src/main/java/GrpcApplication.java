import controller.ServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcApplication {
  public static void main(String[] args) {
    try {
      final Server server = ServerBuilder.forPort(8089).addService(new ServiceImpl()).build();

      server.start();
      System.out.println("Server started at " + server.getPort());
      server.awaitTermination();
    } catch (IOException | InterruptedException e) {
      System.out.println("Error: " + e);
    }
  }
}
