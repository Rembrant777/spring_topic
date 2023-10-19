package com.ema.springboot.grpc.springbootstartergrpc.exception;

import com.ema.springboot.grpc.springbootstartergrpc.service.GrpcResponse;
import lombok.Data;

@Data
public class GrpcResponseException extends RuntimeException {
    private GrpcResponse response;

    public GrpcResponseException(GrpcResponse response) {
        super(response.getMessage());
        this.response = response;
    }
}
