from locust import task, tag
from data.product_data import ProductData
import grpc_user
import service_pb2
import service_pb2_grpc


class GrpcUser(grpc_user.GrpcUser):
    host = "localhost:8080"
    stub_class = service_pb2_grpc.ServiceStub

    @tag("small")
    @task
    def small(self):
        product_info = service_pb2.Product(id=0,
                                           name=ProductData.get_small_product_name(),
                                           description=ProductData.get_small_product_description())
        request = service_pb2.ProductRequest(product=product_info)

        # Send request
        self.stub.Echo(request=request)

        # -- Print response (simple test)
        # response = self.stub.Echo(request=request)
        # print("id: " + str(response.product.id))
        # print("name: " + response.product.name)
        # print("description: " + response.product.description)
        # print()
        
    @tag("typical")
    @task
    def typical(self):
        product_info = service_pb2.Product(id=1,
                                           name=ProductData.get_typical_product_name(),
                                           description=ProductData.get_typical_product_description())
        request = service_pb2.ProductRequest(product=product_info)

        # Send request
        self.stub.Modify(request=request)

    @tag("large")
    @task
    def large(self):
        product_info = service_pb2.Product(id=2,
                                           name=ProductData.get_large_product_name(),
                                           description=ProductData.get_large_product_description())
        request = service_pb2.ProductRequest(product=product_info)

        # Send request
        self.stub.Echo(request=request)

    @tag("stress")
    @task
    def stress(self):
        product_info = service_pb2.Product(id=3,
                                           name=ProductData.get_large_product_name(),
                                           description=ProductData.get_large_product_description())
        request = service_pb2.ProductRequest(product=product_info)

        # Send request
        self.stub.Modify(request=request)
