package com.ema.springboot.grpc.service.impl;

import com.ema.springboot.grpc.model.GrpcRequest;
import com.ema.springboot.grpc.model.GrpcResponse;
import com.ema.springboot.grpc.rpc.GrpcService;
import com.ema.springboot.grpc.service.SerializeService;
import com.google.protobuf.ByteString;

public class ProtoStuffSerializeService implements SerializeService {
    @Override
    public ByteString serialize(GrpcResponse response) {
        return null;
    }

    @Override
    public ByteString serialize(GrpcRequest request) {
        return null;
    }

    @Override
    public GrpcRequest deserialize(GrpcService.Request request) {
        return null;
    }

    @Override
    public GrpcResponse deserialize(GrpcService.Response response) {
        return null;
    }
}
