from locust import HttpUser, task

class RestUser(HttpUser):
    
    @task
    def echo_small(self):
        
        request = {"id": 0,
                   "name": "Cookie",
                   "description": "A tasty cookie."}
        
        response = self.client.post(url="/echo", json=request)
        print(response.text)