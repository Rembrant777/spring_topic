package com.ema.springboot.grpc.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.ema.springboot.grpc.constant.GrpcResponseStatus;
import com.ema.springboot.grpc.model.GrpcRequest;
import com.ema.springboot.grpc.model.GrpcResponse;
import com.ema.springboot.grpc.rpc.GrpcService;
import com.google.protobuf.ByteString;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class SerializeServiceTest {

    @Test
    public void testSerializeGrpcResponse() {

    }


    static class MockSerializeService implements SerializeService {

        @Override
        public ByteString serialize(GrpcResponse response) {
            Assert.notNull(response, "Cannot serialize null response object!");
            return ByteString.copyFrom(JSON.toJSONBytes(response));
        }

        @Override
        public ByteString serialize(GrpcRequest request) {
            Assert.notNull(request, "Cannot serialize null request object!");
            return ByteString.copyFrom(JSON.toJSONBytes(request));
        }

        @Override
        public GrpcRequest deserialize(GrpcService.Request request) {
            byte [] bytes = request.getRequest().toByteArray();
            return JSON.parseObject(bytes, GrpcRequest.class, Feature.OrderedField);
        }

        @Override
        public GrpcResponse deserialize(GrpcService.Response response) {
            byte [] bytes = response.getResponse().toByteArray();
            return JSON.parseObject(bytes, GrpcResponse.class, Feature.OrderedField);
        }
    }


    private GrpcResponse genMockGrpcResponse() {
        return null;
    }

    private GrpcRequest genMockGrpcRequest() {
        return null;
    }

    private GrpcService.Request genMockGrpcServiceRequest() {
        return null;
    }

    private GrpcService.Response genMockGrpcServiceResponse () {
        return null;
    }
}