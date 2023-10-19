package com.ema.springboot.grpc.springbootstartergrpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.ema.springboot.grpc.springbootstartergrpc.GrpcService;
import com.ema.springboot.grpc.springbootstartergrpc.service.GrpcRequest;
import com.ema.springboot.grpc.springbootstartergrpc.service.GrpcResponse;
import com.ema.springboot.grpc.springbootstartergrpc.service.SerializeService;
import com.google.protobuf.ByteString;

/**
 * FastJSON serialize/deserilize  
 */
public class FastJSONSerializeService implements SerializeService {

    public ByteString serialize(GrpcResponse response) {
        return ByteString.copyFrom(JSON.toJSONBytes(response));
    }

    public ByteString serialize(GrpcRequest request) {
        return ByteString.copyFrom(JSON.toJSONBytes(request));
    }

    public GrpcRequest deserialize(GrpcService.Request request) {
        byte [] bytes = request.getRequest().toByteArray();
        return JSON.parseObject(bytes, GrpcRequest.class, Feature.OrderedField);
    }

    public GrpcResponse deserialize(GrpcService.Response response) {
        byte [] bytes = response.getResponse().toByteArray();
        return JSON.parseObject(bytes, GrpcResponse.class, Feature.OrderedField);
    }
}
