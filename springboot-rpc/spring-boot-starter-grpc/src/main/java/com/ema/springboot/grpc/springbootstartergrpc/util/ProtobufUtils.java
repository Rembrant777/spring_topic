package com.ema.springboot.grpc.springbootstartergrpc.util;

import com.google.common.collect.Maps;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;

public class ProtobufUtils {
    // schema cache map
    private static Map<Class<?>, RuntimeSchema<?>> cachedSchema = Maps.newConcurrentMap();

    /**
     * retrieve schema
     */
    private static <T> RuntimeSchema<T> getSchema(Class<T> clazz) {
        RuntimeSchema<T> schema = (RuntimeSchema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }

    /**
     * serialization method, converted the array of objects into the byte array
     *
     * @param obj the object instance
     * @return byte array of class T
     */
    public static <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        RuntimeSchema<T> schema = getSchema(clazz);
        // allocate corresponding memory space to the instance of object with default len = 512 bytes
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        return ProtobufIOUtil.toByteArray(obj, schema, buffer);
    }

    /**
     * de-serialize the array of object, build an instance of the given type T from a series of byte array.
     *
     * @param data  byte array of the T type class instance
     * @param clazz class definition of the class instance
     * @return instance of the correspoinding class
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        // retrieve schema of the class from the class definition
        RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
        T message = schema.newMessage();
        ProtobufIOUtil.mergeFrom(data, message, schema);
        return message;
    }
}
