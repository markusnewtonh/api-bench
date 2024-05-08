from locust import HttpUser, task, tag
from data.product_data import ProductData
import os


class RestTlsUser(HttpUser):
    host = "https://localhost:8080"
    ca_cert_path = os.path.join(os.path.dirname(
        __file__), '..', '..', 'certs', 'ca-cert.pem')

    @tag("small")
    @task
    def small(self):
        request = {
            "id": 0,
            "name": ProductData.get_small_product_name(),
            "description": ProductData.get_small_product_description()
        }

        self.client.post(url="/echo", json=request, verify=self.ca_cert_path)

        # response = self.client.post(url="/echo", json=request, verify=self.ca_cert_path)
        # print(response.text)

    @tag("typical")
    @task
    def typical(self):
        request = {
            "id": 1,
            "name": ProductData.get_typical_product_name(),
            "description": ProductData.get_typical_product_description()
        }

        self.client.post(url="/modify", json=request, verify=self.ca_cert_path)

    @tag("large")
    @task
    def large(self):
        request = {
            "id": 2,
            "name": ProductData.get_large_product_name(),
            "description": ProductData.get_large_product_description()
        }

        self.client.post(url="/echo", json=request)

    @tag("stress")
    @task
    def stress(self):
        request = {
            "id": 3,
            "name": ProductData.get_large_product_name(),
            "description": ProductData.get_large_product_description()
        }

        self.client.post(url="/modify", json=request)
