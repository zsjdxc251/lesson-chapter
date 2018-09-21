package com.chapter.distributed.serializer;

import org.apache.commons.lang3.StringUtils;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhengshijun
 * @version created on 2018/5/29.
 */
public class MessagePackSerializer implements ISerializer {

    private static final Logger log = LoggerFactory.getLogger(MessagePackSerializer.class);

    @Override
    public byte[] encoder(Object entity) {
        MessagePack msgpack = new MessagePack();
        byte[] result = null;
        try{
            result = msgpack.write(entity);
        }catch (IOException e){
            log.error(StringUtils.EMPTY,e);
        }
        log.debug("encode byte length:{}",result.length);
        return result;
    }


    @Override
    public <T> T decoder(byte[] coder, Class<T> entry) {
        MessagePack msgpack = new MessagePack();

        T t = null;
        try {
            t = msgpack.read(coder,entry);
        } catch (IOException e) {
            log.error(StringUtils.EMPTY,e);
        }
        return t;
    }
}
