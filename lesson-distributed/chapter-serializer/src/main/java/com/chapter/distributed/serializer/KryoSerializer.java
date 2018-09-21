package com.chapter.distributed.serializer;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author zhengshijun
 * @version created on 2018/5/29.
 */
public class KryoSerializer implements ISerializer {

    private static final ThreadLocal<Kryo> kryoLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        return kryo;
    });


    @Override
    public byte[] encoder(Object entity) {
        Kryo kryo = kryoLocal.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeObject(output, entity);
        output.close();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T decoder(byte[] coder, Class<T> entry) {
        Kryo kryo = kryoLocal.get();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(coder);
        Input input = new Input(byteArrayInputStream);
        input.close();
        return (T) kryo.readObject(input, entry);
    }
}
