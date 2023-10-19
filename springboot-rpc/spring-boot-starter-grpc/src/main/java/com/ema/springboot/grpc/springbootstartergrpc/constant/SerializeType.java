package com.ema.springboot.grpc.springbootstartergrpc.constant;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public enum SerializeType {
    SOFAHESSIAN(1, SofaHessianSerializeService.class),
    PROTOSTUFF(2, ProtoStuffSerializeService.class),
    FASTJSON(3, FastJSONSerializeService.class);

    private static Map<Integer, SerializeType> enumMap = Maps.newHashMap();

    static {
        for (SerializeType serializeType : SerializeType.values()) {
            enumMap.put(serializeType.value, serializeType);
        }
    }

    private int value;

    private Class clazz;

    SerializeType(int value, Class clazz) {
        this.clazz = clazz;
        this.value = value;
    }

    public static SerializeType getSerializerTypeByValue(int value) {
        return enumMap.get(value);
    }

    public int getValue() {
        return value;
    }

    public Class getClazz() {
        return clazz;
    }
}
