package com.ema.springboot.grpc.springbootstartergrpc.exception;

public class GrpcException extends RuntimeException {

    public GrpcException(String message) {
        super(message);
    }
}
