package com.ema.springboot.grpc.constant;

/**
 * enumeration that inner defines all the status code value
 * represent different status on the server side.
 */
public enum GrpcResponseStatus {
    SUCCESS(0), ERROR(-1);

    private int code;

    GrpcResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
