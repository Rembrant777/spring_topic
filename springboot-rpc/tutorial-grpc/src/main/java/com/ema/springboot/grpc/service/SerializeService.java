package com.ema.springboot.grpc.service;

import com.ema.springboot.grpc.model.GrpcRequest;
import com.ema.springboot.grpc.model.GrpcResponse;
import com.ema.springboot.grpc.rpc.GrpcService;
import com.google.protobuf.ByteString;

/**
 * Definition of global serialize/deserialize protocol.
 */
public interface SerializeService {
    /**
     * RPC Response Serialize
     */
    ByteString serialize(GrpcResponse response);

    /**
     * RPC Request Serialize
     */
    ByteString serialize(GrpcRequest request);

    /***
     * Response deserialize
     */
    GrpcRequest deserialize(GrpcService.Request request);

    /**
     * Request deserialize
     */
    GrpcResponse deserialize(GrpcService.Response response);
}
