package com.chapter.distributed.activemq.spring.converter;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Random;

/**
 * @author zhengshijun
 * @version created on 2018/6/11.
 */
public class CustomMessageConverter implements MessageConverter {



    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        TextMessage textMessage = session.createTextMessage((String)object);
        textMessage.setStringProperty("domain", new Random().nextBoolean()?"xxx.2":"xxx.1");
        return textMessage;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {

        //return new JSONObject().put("message",((TextMessage)message).getText());
        //message.setStringProperty("domain", new Random().nextBoolean()?"xxx.2":"xxx.1");
        return message;
    }
}
