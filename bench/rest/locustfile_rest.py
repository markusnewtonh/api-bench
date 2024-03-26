from locust import HttpUser, task, tag
from data.product_data import ProductData


class RestUser(HttpUser):
    host = "http://localhost:8080"

    @tag("small")
    @task
    def small(self):
        request = {
            "id": 0,
            "name": ProductData.get_small_product_name(),
            "description": ProductData.get_small_product_description()
        }

        self.client.post(url="/echo", json=request)

        # response = self.client.post(url="/echo", json=request)
        # print(response.text)

    @tag("typical")
    @task
    def typical(self):
        request = {
            "id": 1,
            "name": ProductData.get_typical_product_name(),
            "description": ProductData.get_typical_product_description()
        }

        self.client.post(url="/modify", json=request)

    @tag("max")
    @task
    def max(self):
        request = {
            "id": 2,
            "name": ProductData.get_max_product_name(),
            "description": ProductData.get_max_product_description()
        }

        self.client.post(url="/echo", json=request)

    @tag("stress")
    @task
    def stress(self):
        request = {
            "id": 3,
            "name": ProductData.get_max_product_name(),
            "description": ProductData.get_max_product_description()
        }

        self.client.post(url="/modify", json=request)
