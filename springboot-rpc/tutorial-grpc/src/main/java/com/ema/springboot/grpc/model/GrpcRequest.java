package com.ema.springboot.grpc.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GrpcRequest implements Serializable {
    private static final long serialVersionUID = 72893091203213408L;

    /**
     * interface
     */
    private String clazz;

    /**
     * method
     */
    private String method;

    /**
     * service parameters
     */
    private Object[] args;
}
