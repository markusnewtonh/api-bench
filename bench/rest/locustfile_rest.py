from locust import HttpUser, task
from data.product_data import ProductData


class RestUser(HttpUser):
    host = "http://localhost:8080"
    
    @task
    def small(self):
        request = {"id": 0,
                   "name": ProductData.get_small_product_name(),
                   "description": ProductData.get_small_product_description()}
        
        self.client.post(url="/echo", json=request)
        # response = self.client.post(url="/echo", json=request)
        # print(response.text)
        
    @task
    def typical(self):
        request = {"id": 1,
                   "name": ProductData.get_typical_product_name(),
                   "description": ProductData.get_typical_product_description()}
        
        self.client.post(url="/modify", json=request)
        
    @task
    def max(self):
        request = {"id": 2,
                   "name": ProductData.get_max_product_name(),
                   "description": ProductData.get_max_product_description()}
        
        self.client.post(url="/echo", json=request)
        
    @task
    def stress(self):
        request = {"id": 3,
                   "name": ProductData.get_max_product_name(),
                   "description": ProductData.get_max_product_description()}
        
        self.client.post(url="/modify", json=request)