package com.ema.springboot.grpc.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * here we define the service of Grpc
 * </pre>
 */
@jakarta.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CommonServiceGrpc {

  private CommonServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "CommonService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ema.springboot.grpc.rpc.GrpcService.Request,
      com.ema.springboot.grpc.rpc.GrpcService.Response> getHandleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "handle",
      requestType = com.ema.springboot.grpc.rpc.GrpcService.Request.class,
      responseType = com.ema.springboot.grpc.rpc.GrpcService.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ema.springboot.grpc.rpc.GrpcService.Request,
      com.ema.springboot.grpc.rpc.GrpcService.Response> getHandleMethod() {
    io.grpc.MethodDescriptor<com.ema.springboot.grpc.rpc.GrpcService.Request, com.ema.springboot.grpc.rpc.GrpcService.Response> getHandleMethod;
    if ((getHandleMethod = CommonServiceGrpc.getHandleMethod) == null) {
      synchronized (CommonServiceGrpc.class) {
        if ((getHandleMethod = CommonServiceGrpc.getHandleMethod) == null) {
          CommonServiceGrpc.getHandleMethod = getHandleMethod =
              io.grpc.MethodDescriptor.<com.ema.springboot.grpc.rpc.GrpcService.Request, com.ema.springboot.grpc.rpc.GrpcService.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "handle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ema.springboot.grpc.rpc.GrpcService.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ema.springboot.grpc.rpc.GrpcService.Response.getDefaultInstance()))
              .setSchemaDescriptor(new CommonServiceMethodDescriptorSupplier("handle"))
              .build();
        }
      }
    }
    return getHandleMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CommonServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommonServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommonServiceStub>() {
        @java.lang.Override
        public CommonServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommonServiceStub(channel, callOptions);
        }
      };
    return CommonServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CommonServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommonServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommonServiceBlockingStub>() {
        @java.lang.Override
        public CommonServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommonServiceBlockingStub(channel, callOptions);
        }
      };
    return CommonServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CommonServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommonServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommonServiceFutureStub>() {
        @java.lang.Override
        public CommonServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommonServiceFutureStub(channel, callOptions);
        }
      };
    return CommonServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * here we define the service of Grpc
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void handle(com.ema.springboot.grpc.rpc.GrpcService.Request request,
        io.grpc.stub.StreamObserver<com.ema.springboot.grpc.rpc.GrpcService.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHandleMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CommonService.
   * <pre>
   * here we define the service of Grpc
   * </pre>
   */
  public static abstract class CommonServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CommonServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CommonService.
   * <pre>
   * here we define the service of Grpc
   * </pre>
   */
  public static final class CommonServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CommonServiceStub> {
    private CommonServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommonServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommonServiceStub(channel, callOptions);
    }

    /**
     */
    public void handle(com.ema.springboot.grpc.rpc.GrpcService.Request request,
        io.grpc.stub.StreamObserver<com.ema.springboot.grpc.rpc.GrpcService.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHandleMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CommonService.
   * <pre>
   * here we define the service of Grpc
   * </pre>
   */
  public static final class CommonServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CommonServiceBlockingStub> {
    private CommonServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommonServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommonServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ema.springboot.grpc.rpc.GrpcService.Response handle(com.ema.springboot.grpc.rpc.GrpcService.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHandleMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CommonService.
   * <pre>
   * here we define the service of Grpc
   * </pre>
   */
  public static final class CommonServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CommonServiceFutureStub> {
    private CommonServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommonServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommonServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ema.springboot.grpc.rpc.GrpcService.Response> handle(
        com.ema.springboot.grpc.rpc.GrpcService.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHandleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HANDLE:
          serviceImpl.handle((com.ema.springboot.grpc.rpc.GrpcService.Request) request,
              (io.grpc.stub.StreamObserver<com.ema.springboot.grpc.rpc.GrpcService.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getHandleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ema.springboot.grpc.rpc.GrpcService.Request,
              com.ema.springboot.grpc.rpc.GrpcService.Response>(
                service, METHODID_HANDLE)))
        .build();
  }

  private static abstract class CommonServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CommonServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ema.springboot.grpc.rpc.GrpcService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CommonService");
    }
  }

  private static final class CommonServiceFileDescriptorSupplier
      extends CommonServiceBaseDescriptorSupplier {
    CommonServiceFileDescriptorSupplier() {}
  }

  private static final class CommonServiceMethodDescriptorSupplier
      extends CommonServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CommonServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CommonServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CommonServiceFileDescriptorSupplier())
              .addMethod(getHandleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
