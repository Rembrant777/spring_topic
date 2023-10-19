package com.ema.springboot.grpc.springbootstartergrpc.service.impl;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
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
import java.io.OutputStream;
import java.util.Arrays;

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
        UnsafeByteArrayOutputStream byteArray = new UnsafeByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(byteArray);
        try {
            output.setSerializerFactory(serializerFactory);
            output.writeObject(obj);
            output.close();
             return byteArray.toByteArray();
        } catch (Exception e) {
            throw new GrpcException("sofa-hessian serialize fail: " + e.getMessage());
        }
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
        mPosition += len;
        return len;
    }

    /**
     * Method to skip how many (len) bytes over the
     * inner defined byte array.
     * <p>
     * It depends on different situations
     * mPosition: current read position of the reading contents.
     * mLimit: maximum length of the inner byte array that can be get access to not raise out of index exception.
     * len: how many bytes need to skip
     * <p>
     * case-1: if (mPosition  +len) > mLimit, it means if let index skip len bytes it will jump out of the index of the byte array.
     * so maximum jump steps = max length - current index position which can be calculated as {mLimit - mPosition}.
     * <p>
     * case-2: if len <= 0, this means input param of len is an invalue value skip nothing just return 0
     * <p>
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
     * <p>
     * cuz we know that the total space of bytes in the byte array is the mLimit.
     * and the current pointer value in the byte array is the mPosition.
     * so obviously the available(can set byte elements in) is ${totalLen} - ${currentIndex} which can
     * be represented as mLimit - mPosition.
     */
    @Override
    public int available() throws IOException {
        return mLimit - mPosition;
    }

    /**
     * Method to check whether current {@link InputStream} subclass implements(supports)
     * {@link InputStream#mark(int)} and {@link InputStream#reset()} two methods.
     * Base class {@link InputStream} does not provides two methods' specific implementation.
     */
    @Override
    public boolean markSupported() {
        return true;
    }

    /**
     * Method to set mMark value from the current index position.
     * we invoke method in the scenario of switch write mode into the reading mode.
     * So can read from the start position to the mMark [0, mMark] ... I suppose so.
     */
    @Override
    public synchronized void mark(int readAHeadLimit) {
        mMark = mPosition;
    }

    /**
     * Method to reset the mMark value to the current index position.
     *
     */
    @Override
    public synchronized void reset() throws IOException {
        mPosition = mMark;
    }

    /**
     * method to close the input stream.
     */
    @Override
    public void close() throws IOException {
        super.close();
    }
}

/**
 * refer to code：
 * https://github.com/alipay/sofa-rpc/blob/master/core/common/src/main/java/com/alipay/sofa/rpc/common/struct/UnsafeByteArrayOutputStream.java
 */

@NotThreadSafe
class UnsafeByteArrayOutputStream extends OutputStream {
    // inner byte array
    byte [] mBuffer;

    int mCount;

    // default constructor to construct byte array with the length of 32 byte
    UnsafeByteArrayOutputStream() {
        this(32);
    }

    UnsafeByteArrayOutputStream(int size) {
        if (size < 0) {
            // size negative invalid input throw exception
            throw new IllegalArgumentException("Negative initial size: " + size);
        }

        mBuffer = new byte[size];
    }

    /**
     * method to append new value with the type of `int` to the inner byte array.
     * @param b to be appended element with the type of `int`.
     */
    @Override
    public void write(int b) throws IOException {
        int newCount = mCount + 1;
        if (newCount > mBuffer.length) {
            // inner space not enough, apply for extra storage space and copy data
            // from previous space to new allocated one

            // mBuffer.length << 1 means apply for an extra space with the length = mBuffer.length * 2
            //  System.arraycopy(original, 0, copy, 0,
            //                         Math.min(original.length, newLength));
            mBuffer = Arrays.copyOf(mBuffer, Math.max(mBuffer.length << 1, newCount));
        }
        mBuffer[mCount] = (byte) b;
        mCount = newCount;
    }

    /**
     * method to append to be appended byte [] b's b[off, off+len] elements to inner defined byte [] a.
     *
     * @param b  to be appended byte array
     * @param off start position locates in the to be appended byte [] b.
     * @param len how many elements from b[offset] ... b[offset+len] copied from byte [] b to byte [] a which this InputStream subclass inner defined.
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        // 1. first validate the input parameters value
        if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        }

        if (len == 0) {
            return;
        }

        // mCount means current inner byte [] a's current index postion.
        // mCount + len means after appending how many elements in total
        int newCount = mCount + len;

        if (newCount > mBuffer.length) {
            mBuffer = Arrays.copyOf(mBuffer, Math.max(mBuffer.length << 1, newCount));
        }

        System.arraycopy(b, off, mBuffer, mCount, len);

        // after success append, do not forget update previous position index value forward to the last element in the inner defined byte array.
        mCount = newCount;
    }

    // method to deep clone a byte array from the inner defined byte array in case of
    // other modification of the methods may affect the inner defined byte array.
    byte [] toByteArray() {
        return Arrays.copyOf(mBuffer, mCount);
    }

    /**
     * method to convert inner defined byte [] array into string
     */
    @Override
    public String toString() {
        return new String(mBuffer, 0, mCount);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}


