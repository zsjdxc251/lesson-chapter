package com.chapter.distributed.serializer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class JavaSerializer implements ISerializer {

    private static final Logger log = LoggerFactory.getLogger(JavaSerializer.class);
    @Override
    public <T> byte[] encoder(T object) {

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(object);
            log.info("encoder");
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);
        }
        return null;
    }

    @Override
    public <T> T decoder(byte[] bytes, Class<T> entry) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T)objectInputStream.readObject();
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);
        }
        return null;
    }
}
