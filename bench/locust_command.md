To start Locust Web-UI, run the following command in this dir:

`locust -f . --class-picker --processes -1 -t 120s --tag <request type>`

For gRPC benchmarking `Host` must be manually overridden to `localhost:8080` (instead of Locust default setting "http://localhost:8080")
