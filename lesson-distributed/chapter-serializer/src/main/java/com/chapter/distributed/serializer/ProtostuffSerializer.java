package com.chapter.distributed.serializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.apache.commons.lang3.StringUtils;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengshijun
 * @version created on 2018/5/29.
 */
public class ProtostuffSerializer implements ISerializer {

    private static final Logger log = LoggerFactory.getLogger(ProtostuffSerializer.class);

    private Objenesis objenesis = new ObjenesisStd();

    @Override
    public byte[] encoder(Object entity) {
        Class clz = entity.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = RuntimeSchema.createFrom(clz);
            return ProtostuffIOUtil.toByteArray(entity, schema, buffer);
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);
        } finally {
            buffer.clear();
        }
        return null;
    }


    @Override
    public <T> T decoder(byte[] coder, Class<T> entry) {
        T message = objenesis.newInstance(entry);
        Schema<T> schema = RuntimeSchema.createFrom(entry);
        ProtostuffIOUtil.mergeFrom(coder, message, schema);
        return message;
    }
}
