package com.ema.springboot.grpc.springbootstartergrpc.service;

import com.ema.springboot.grpc.springbootstartergrpc.constant.GrpcResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class GrpcResponse implements Serializable {
    private static final long serialVersionUID = 1729374729374047193L;

    /**
     * Response status
     */
    private int status;

    /**
     * Message prompted.
     */
    private String message;

    /**
     * return object result
     */
    private Object result;

    /**
     * server side's exception
     */
    private Throwable exception;

    /**
     * Exception Stack Info
     */
    private StackTraceElement [] stackTrace;

    public GrpcResponse error(String message, Throwable exception, StackTraceElement [] stackTrace) {
        this.status = GrpcResponseStatus.ERROR.getCode();
        this.message = message;
        this.exception = exception;
        this.stackTrace = stackTrace;
        return this ;
    }

    public GrpcResponse success(Object result) {
        this.status = GrpcResponseStatus.SUCCESS.getCode();
        this.result = result;
        return this;
    }


}
