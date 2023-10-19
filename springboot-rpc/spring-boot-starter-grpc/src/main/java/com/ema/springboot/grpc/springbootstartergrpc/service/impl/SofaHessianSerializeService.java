package com.ema.springboot.grpc.springbootstartergrpc.service.impl;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.SerializerFactory;
import com.ema.springboot.grpc.springbootstartergrpc.GrpcService;
import com.ema.springboot.grpc.springbootstartergrpc.exception.GrpcException;
import com.ema.springboot.grpc.springbootstartergrpc.service.GrpcRequest;
import com.ema.springboot.grpc.springbootstartergrpc.service.GrpcResponse;
import com.ema.springboot.grpc.springbootstartergrpc.service.SerializeService;
import com.google.protobuf.ByteString;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;
import java.io.InputStream;

/**
 * sofa-hession serialize/de-serialize tools
 * refer to :
 * https://github.com/alipay/sofa-rpc/blob/master/extension-impl/codec-sofa-hessian/src/main/java/com/alipay/sofa/rpc/codec/sofahessian/SofaHessianSerializer.java
 */
public class SofaHessianSerializeService implements SerializeService {
    private SerializerFactory serializerFactory = new SerializerFactory();

    public ByteString serialize(GrpcResponse response) {
        byte[] bytes = encode(response);
        return ByteString.copyFrom(bytes);
    }

    public ByteString serialize(GrpcRequest request) {
        byte[] bytes = encode(request);
        return ByteString.copyFrom(bytes);
    }

    public GrpcRequest deserialize(GrpcService.Request request) {
        byte[] bytes = request.getRequest().toByteArray();
        UnsafeByteArrayInputStream inputStream = new UnsafeByteArrayInputStream(bytes);
        try {
            Hessian2Input input = new Hessian2Input(inputStream);
            input.setSerializerFactory(serializerFactory);
            GrpcRequest grpcRequest = (GrpcRequest) input.readObject();
            input.close();
            return grpcRequest;
        } catch (IOException e) {
            throw new GrpcException("sofa-henssian deserialize fail: " + e.getMessage());
        }
    }

    public GrpcResponse deserialize(GrpcService.Response response) {
        byte[] bytes = response.getResponse().toByteArray();
        UnsafeByteArrayInputStream inputStream = new UnsafeByteArrayInputStream(bytes);
        try {
            Hessian2Input input = new Hessian2Input(inputStream);
            input.setSerializerFactory(serializerFactory);
            GrpcResponse grpcResponse = (GrpcResponse) input.readObject();
            input.close();
            return grpcResponse;
        } catch (IOException ex) {
            throw new GrpcException("sofa-hessian deserialize fail: " + ex.getMessage());
        }
    }

    /**
     * method to execute object's encode execution.
     * in this class supports hessian serialize strategy.
     */
    private byte[] encode(Object obj) {

    }
}

/**
 * Class refer to:
 * https://github.com/alipay/sofa-rpc/blob/master/core/common/src/main/java/com/alipay/sofa/rpc/common/struct/UnsafeByteArrayInputStream.java
 */
@NotThreadSafe
class UnsafeByteArrayInputStream extends InputStream {
    byte[] mData;

    int mPosition, mLimit, mMark = 0;

    UnsafeByteArrayInputStream(byte[] buf) {
        this(buf, 0, buf.length);
    }

    UnsafeByteArrayInputStream(byte[] buf, int offset, int length) {
        mData = buf;
        mPosition = mMark = offset;
        mLimit = Math.min(offset + length, buf.length);
    }

    public int read() throws IOException {
        return (mPosition < mLimit) ? (mData[mPosition++] & 0xff) : -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }

        if (mPosition >= mLimit) {
            return -1;
        }

        if (mPosition + len > mLimit) {
            len = mLimit - mPosition;
        }

        if (len <= 0) {
            return 0;
        }

        System.arraycopy(mData, mPosition, b, off, len);
        mPosition += len ;
        return len;
    }

    /**
     * Method to skip how many (len) bytes over the
     * inner defined byte array.
     *
     * It depends on different situations
     * mPosition: current read position of the reading contents.
     * mLimit: maximum length of the inner byte array that can be get access to not raise out of index exception.
     * len: how many bytes need to skip
     *
     * case-1: if (mPosition  +len) > mLimit, it means if let index skip len bytes it will jump out of the index of the byte array.
     * so maximum jump steps = max length - current index position which can be calculated as {mLimit - mPosition}.
     *
     * case-2: if len <= 0, this means input param of len is an invalue value skip nothing just return 0
     *
     * case-3: suppose current position skip over len elements in the byte array, but final position still < mLimit not raise out of index exception
     * then return total jump steps the value of len, and also reset the pointer of the position (mPosition) to the new jumped position that with the
     * value equals to len + mPosition.
     *
     * @param len how many bytes to skip
     * @return final skip over bytes in the byte array.
     */
    @Override
    public long skip(long len) throws IOException {
        if (mPosition + len > mLimit) {
            len = mLimit - mPosition;
        }

        if (len <= 0) {
            return 0;
        }

        mPosition += len;
        return len;
    }

    /**
     * method to represent how many bytes in total available in the byte array.
     *
     * cuz we know that the total space of bytes in the byte array is the mLimit.
     * and the current pointer value in the byte array is the mPosition.
     * so obviously the available(can set byte elements in) is ${totalLen} - ${currentIndex} which can
     * be represented as mLimit - mPosition.
     */
    @Override
    public int available() throws IOException {
        return mLimit - mPosition;
    }
}


