from locust import events, task

import grpc_user
import service_pb2
import service_pb2_grpc


class GrpcBench(grpc_user.GrpcUser):
    host = "localhost:8080"
    stub_class = service_pb2_grpc.ServiceStub

    @task
    def echo_small(self):
        product_info = service_pb2.Product(id=0, name="Pasta", description="Aldente pasta.")
        request = service_pb2.ProductRequest(product=product_info)
        
        response = self.stub.Echo(request=request)
        # print("id: " + str(response.product.id))
        # print("name: " + response.product.name)
        # print("description: " + response.product.description)
        # print()
        
