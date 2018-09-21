package com.chapter.distributed.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhengshijun
 * @version created on 2018/9/21.
 */
public class HessianSerializer implements ISerializer {

    @Override
    public <T> byte[] encoder(T object) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        try {
            ho.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    @Override
    public <T> T decoder(byte[] bytes, Class<T> entry) {
        InputStream inputStream  = new ByteArrayInputStream(bytes);
        HessianInput hessian = new HessianInput(inputStream);
        T t = null;
        try{
            t = (T) hessian.readObject();
        }catch (IOException e){
            e.printStackTrace();;
        }
        return t;
    }
}
