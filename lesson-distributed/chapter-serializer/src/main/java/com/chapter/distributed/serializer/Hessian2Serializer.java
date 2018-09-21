package com.chapter.distributed.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author zhengshijun
 * @version created on 2018/9/21.
 */
public class Hessian2Serializer implements ISerializer {

    @Override
    public <T> byte[] encoder(T object) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {

            Hessian2Output out = new Hessian2Output(bos);
            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T decoder(byte[] bytes, Class<T> entry) {
        try {
            Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(bytes));
            return (T) input.readObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
