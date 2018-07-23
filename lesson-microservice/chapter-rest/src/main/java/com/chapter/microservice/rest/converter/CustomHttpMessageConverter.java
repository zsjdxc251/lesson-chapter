package com.chapter.microservice.rest.converter;

import com.chapter.microservice.rest.entitry.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class CustomHttpMessageConverter extends AbstractHttpMessageConverter<Person> {





    public CustomHttpMessageConverter() {
        super(MediaType.valueOf("application/properties"));
        setDefaultCharset(Charset.defaultCharset());
    }


    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Person.class);
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return super.canRead(mediaType);
    }

    @Override
    protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {


        InputStream inputStream = inputMessage.getBody();

        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,getDefaultCharset()));

        return new Person(properties.getProperty("person.name"),properties.getProperty("person.address"),Integer.valueOf(properties.getProperty("person.age")));
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream outputStream = outputMessage.getBody();

        Properties properties = new Properties();
        properties.put("person.name",person.getName());
        properties.put("person.address",person.getAddress());
        properties.put("person.age",String.valueOf(person.getAge()));

        properties.store(new OutputStreamWriter(outputStream, getDefaultCharset()),"peroperties 注释");

    }
}
