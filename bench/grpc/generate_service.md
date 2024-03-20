Edit and run the following to generate gRPC service code for the Locust client:

`python -m grpc_tools.protoc -Iprotos --python_out=. --pyi_out=. --grpc_python_out=. protos/service.proto`
