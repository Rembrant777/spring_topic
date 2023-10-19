package com.ema.springboot.grpc.springbootstartergrpc.service;

import com.ema.springboot.grpc.springbootstartergrpc.GrpcService;
import com.google.protobuf.ByteString;

public interface SerializeService {
    /**
     * serialize
     */
    ByteString serialize(GrpcResponse response);

    /**
     * serialize
     */
    ByteString serialize(GrpcRequest request);

    /**
     * de-serialize
     */
    GrpcRequest deserialize(GrpcService.Request request);

    /**
     * de-serialize
     */
    GrpcResponse deserialize(GrpcService.Response response);
}
