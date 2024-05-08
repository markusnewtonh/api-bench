import controller.ServiceImpl;
import io.grpc.*;

import java.io.File;
import java.io.IOException;

public class GrpcTlsApplication {
  public static void main(String[] args) {
    try {
      final Server server =
          ServerBuilder.forPort(8080)
              .addService(new ServiceImpl())
              // Enable TLS
              .useTransportSecurity(
                  new File("src/main/resources/certs/server-cert.pem"),
                  new File("src/main/resources/certs/server-key.pem"))
              .build();

      server.start();
      System.out.println("Server started at " + server.getPort());
      server.awaitTermination();
    } catch (IOException | InterruptedException e) {
      System.out.println("Error: " + e);
    }
  }
}
