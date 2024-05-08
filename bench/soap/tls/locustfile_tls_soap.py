from locust import HttpUser, task, tag
from zeep import Client
from zeep.transports import Transport
from data.product_data import ProductData
import os


class SoapTlsUser(HttpUser):
    host = "https://localhost:8080"
    ca_cert_path = os.path.join(os.path.dirname(
        __file__), '..', '..', 'certs', 'ca-cert.pem')

    def __init__(self, *args, **kwargs):
        super(SoapTlsUser, self).__init__(*args, **kwargs)
        wsdl = "https://localhost:8080/ws/soapservice.wsdl"
        self.client.verify = self.ca_cert_path
        client_session = Transport(session=self.client)
        self.client = Client(wsdl=wsdl, transport=client_session)
        self.client.set_ns_prefix("tns", "https://markusnewtonh.com/api-bench")
        self.product_type = self.client.get_type("tns:product")

    @tag("small")
    @task
    def small(self):
        product_data = self.product_type(
            id=0,
            name=ProductData.get_small_product_name(),
            description=ProductData.get_small_product_description()
        )

        self.client.service.getEcho(product=product_data)

        # uncomment to print response
        # response = self.client.service.getEcho(product=product_data)
        # print(response)

    @tag("typical")
    @task
    def typical(self):
        product_data = self.product_type(
            id=1,
            name=ProductData.get_typical_product_name(),
            description=ProductData.get_typical_product_description()
        )

        self.client.service.getModify(product=product_data)

    @tag("large")
    @task
    def large(self):
        product_data = self.product_type(
            id=2,
            name=ProductData.get_large_product_name(),
            description=ProductData.get_large_product_description()
        )

        self.client.service.getEcho(product=product_data)

    @tag("stress")
    @task
    def stress(self):
        product_data = self.product_type(
            id=3,
            name=ProductData.get_large_product_name(),
            description=ProductData.get_large_product_description()
        )

        self.client.service.getModify(product=product_data)
