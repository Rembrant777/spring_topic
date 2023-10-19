package com.ema.springboot.grpc.springbootstartergrpc.service;

import java.io.Serializable;

public class GrpcRequest implements Serializable {
    private static final long serialVersionUID = -1234890287134532L;
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
