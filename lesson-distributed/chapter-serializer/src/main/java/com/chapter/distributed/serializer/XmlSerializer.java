package com.chapter.distributed.serializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class XmlSerializer implements ISerializer {
    private static final XStream XSTREAM = new XStream(new DomDriver());


    @Override
    public <T> byte[] encoder(T object) {

        return XSTREAM.toXML(object).getBytes();
    }

    @Override
    public <T> T decoder(byte[] bytes, Class<T> entry) {

        return (T)XSTREAM.fromXML(new String(bytes));
    }
}
