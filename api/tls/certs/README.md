### Generate certificates for TLS

1. Edit `gen_cert.sh` so that it contains your correct information for the certificates.

2. Generate TLS certificates by running  `gen_cert.sh`.

3. Copy `ca-cert.pem`, `server-cert.pem`, and `server-key.pem` to each protocols' resource directory. For example, for gRPC, copy to:
`api/tls/grpc/src/main/resources/certs`

4. Copy `ca-cert.pem` to `bench/certs`.
