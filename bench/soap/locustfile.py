from locust import HttpUser, task
from zeep import Client


class SoapUser(HttpUser):

    def __init__(self, *args, **kwargs):
        super(SoapUser, self).__init__(*args, **kwargs)
        wsdl="http://localhost:8080/ws/soapservice.wsdl"
        self.client = Client(wsdl=wsdl)
        self.client.set_ns_prefix("tns", "https://markusnewtonh.com/api-bench")
        
    @task
    def echo_small(self):
        product_type = self.client.get_type("tns:product")

        product_data = product_type(
            id=0,  
            name="Toothbrush",  
            description="Medium softness." 
        )

        response = self.client.service.getEcho(product=product_data)
        # print(response)


# response = self.client.service.getModify(product=product_data)  <-- modify call