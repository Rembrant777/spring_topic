package com.ema.springboot.grpc.springbootstartergrpc.service.impl;

import com.ema.springboot.grpc.springbootstartergrpc.GrpcService;
import com.ema.springboot.grpc.springbootstartergrpc.service.GrpcRequest;
import com.ema.springboot.grpc.springbootstartergrpc.service.GrpcResponse;
import com.ema.springboot.grpc.springbootstartergrpc.service.SerializeService;
import com.google.protobuf.ByteString;

/**
 * ProtoStuff serialize/de-serialize tool
 */
public class ProtoStuffSerializeService implements SerializeService {
    public ByteString serialize(GrpcResponse response) {
        // not implement yet.
        return null;
    }

    public ByteString serialize(GrpcRequest request) {
        return null;
    }

    public GrpcRequest deserialize(GrpcService.Request request) {
        return null;
    }

    public GrpcResponse deserialize(GrpcService.Response response) {
        return null;
    }
}
