from locust import events, task
from data.product_data import ProductData
import grpc_user, service_pb2, service_pb2_grpc


class GrpcUser(grpc_user.GrpcUser):
    host = "localhost:8080"
    stub_class = service_pb2_grpc.ServiceStub

    @task
    def small(self):
        product_info = service_pb2.Product(id=0,
                                           name=ProductData.get_small_product_name(),
                                           description=ProductData.get_small_product_description())
        request = service_pb2.ProductRequest(product=product_info)
        
        # Send request
        self.stub.Echo(request=request)
        
        # -- Print response (simple test)
        response = self.stub.Echo(request=request)
        print("id: " + str(response.product.id))
        print("name: " + response.product.name)
        print("description: " + response.product.description)
        print()
        
    @task
    def typical(self):
        product_info = service_pb2.Product(id=1,
                                           name=ProductData.get_typical_product_name(),
                                           description=ProductData.get_typical_product_description())
        request = service_pb2.ProductRequest(product=product_info)
        
        # Send request
        self.stub.Modify(request=request)
        
    
    @task
    def max(self):
        product_info = service_pb2.Product(id=2,
                                           name=ProductData.get_max_product_name(),
                                           description=ProductData.get_max_product_description())
        request = service_pb2.ProductRequest(product=product_info)
        
        # Send request
        self.stub.Echo(request=request)
        
    
    @task
    def stress(self):
        product_info = service_pb2.Product(id=3,
                                           name=ProductData.get_max_product_name(),
                                           description=ProductData.get_max_product_description())
        request = service_pb2.ProductRequest(product=product_info)
        
        # Send request
        self.stub.Modify(request=request)