package com.ema.springboot.grpc.model;

import com.ema.springboot.grpc.constant.GrpcResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class GrpcResponse implements Serializable {
    private static final long serialVersionUID = -293284934328323L;

    /**
     * response status code
     */
    private int status;

    /**
     * metion message
     */
    private String message;

    /**
     * response result
     */
    private Object result;

    /**
     * server side exception detail
     */
    private Throwable exception;

    /**
     * exception stack message
     */
    private StackTraceElement[] stackTrace;

    public GrpcResponse error (String message, Throwable exception, StackTraceElement [] stackTrace) {
        this.status = GrpcResponseStatus.ERROR.getCode();
        this.message = message;
        this.exception = exception;
        this.stackTrace = stackTrace;
        return this;
    }

    public GrpcResponse success(Object result) {
        this.status = GrpcResponseStatus.SUCCESS.getCode();
        this.result = result;
        return this;
    }
}
