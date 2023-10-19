package com.ema.springboot.grpc.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.ema.springboot.grpc.constant.GrpcResponseStatus;
import com.ema.springboot.grpc.model.GrpcRequest;
import com.ema.springboot.grpc.model.GrpcResponse;
import com.ema.springboot.grpc.rpc.GrpcService;
import com.google.protobuf.ByteString;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SerializeServiceTest {
    @Test
    public void testMockGrpcResponseOK() {
        GrpcResponse response = genMockGrpcResponse(true);
        Assertions.assertTrue(Objects.nonNull(response) && response.getStatus() == GrpcResponseStatus.SUCCESS.getCode());

        response = genMockGrpcResponse(false);
        Assertions.assertTrue(Objects.nonNull(response) && response.getStatus() == GrpcResponseStatus.ERROR.getCode());
    }

    @Test
    public void testMockGrpcRequestOK() {
        GrpcRequest request = genMockGrpcRequest();
        Assertions.assertTrue(Objects.nonNull(request) && request.getMethod().equals("testMethod"));
        Assertions.assertTrue(request.getClazz().equals(this.getClass().getName()));
        Assertions.assertTrue(Objects.deepEquals(request.getArgs(), new Object[]{Integer.class}));
    }

    @Test
    public void testSerializeGrpcResponse() {
        GrpcResponse responseSuccess = genMockGrpcResponse(true);
        GrpcResponse responseError = genMockGrpcResponse(false);

        Assertions.assertNotNull(responseSuccess);
        Assertions.assertNotNull(responseError);

        MockSerializeService mockSerializeService = new MockSerializeService();
        ByteString byteStringSuccess = mockSerializeService.serialize(responseSuccess);
        ByteString byteStringError = mockSerializeService.serialize(responseError);

        Assertions.assertTrue(!byteStringSuccess.isEmpty());
        Assertions.assertTrue(!byteStringError.isEmpty());
    }

    @Test
    public void testSerializeRequest() {
        GrpcRequest grpcRequest = genMockGrpcRequest();
        Assertions.assertNotNull(grpcRequest);

        MockSerializeService mockSerializeService = new MockSerializeService();
        ByteString byteString = mockSerializeService.serialize(grpcRequest);
        Assertions.assertTrue(!byteString.isEmpty());
    }


    @Test
    public void testDeserializeGrpcServiceRequest() {
    }


    /**
     * Little confused here, there is no specific methods defined
     * in the previous method to convert ByteString back into the GrpcRequest.
     * And it is the same to the GrpcResponse.
     *
     * And the GrpcService#Request and Grpcservice#Response are two classes defined in the
     * GrpcService which is generated from the .proto file.
     *
     * add a todo here.
     */
    @Test
    public void testDeserializeGrpcServiceResponse() {
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
            byte[] bytes = request.getRequest().toByteArray();
            return JSON.parseObject(bytes, GrpcRequest.class, Feature.OrderedField);
        }

        @Override
        public GrpcResponse deserialize(GrpcService.Response response) {
            byte[] bytes = response.getResponse().toByteArray();
            return JSON.parseObject(bytes, GrpcResponse.class, Feature.OrderedField);
        }
    }

    private GrpcResponse genMockGrpcResponse(boolean isSuccess) {
        GrpcResponse ret = new GrpcResponse();
        if (isSuccess) {
            return ret.success(UUID.randomUUID());
        } else {
            return ret.error("mock_error_msg", new RuntimeException("mock_exp_msg"),
                    new RuntimeException("mock_exp_msg_stack").getStackTrace());
        }
    }

    private GrpcRequest genMockGrpcRequest() {
        GrpcRequest req = new GrpcRequest();
        req.setClazz(this.getClass().getName());
        req.setArgs(new Object[]{Integer.class});
        String testMethod = getMethod(this.getClass(), "testMethod", new Class<?>[]{Integer.class});

        if (Objects.nonNull(testMethod)) {
            req.setMethod(testMethod);
        } else {
            req.setMethod("");
        }

        return req;
    }


    /**
     * Method to generate mock Grpcservice#Request
     */
    private GrpcService.Request genMockGrpcServiceRequest() {
        return null;
    }

    /**
     * Method to generate mock GrpcService#Response instance
     */
    private GrpcService.Response genMockGrpcServiceResponse() {
        return null;
    }

    /**
     * here we create a test method for MethodUtils@getMatchingMethod
     * to retrieve from current class.
     *
     * @param param for MethodUtils to retrieve
     * @return int declare return type for MethodUtils to match and verify
     */
    private int testMethod(int param) {
        return 0;
    }

    private String getMethod(Class<?> clazz, String methodNameStr, Class<?>[] argTypeArray) {
        Method matchingMethod =
                MethodUtils.getMatchingMethod(this.getClass(), methodNameStr, argTypeArray);
        return Objects.nonNull(matchingMethod) ? matchingMethod.getName() : null;
    }
}