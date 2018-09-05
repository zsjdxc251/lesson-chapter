package com.lesson.distributed.mongo.sample;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * @author zhengshijun
 * @version created on 2018/9/5.
 */
public class MorphiaSample {

    public static void main(String[] args){
        final Morphia morphia = new Morphia();


        morphia.mapPackage("com.lesson.distributed.mongo.sample");

        final Datastore datastore = morphia.createDatastore(new MongoClient("",3333), "morphia_example");


        datastore.ensureIndexes();

    }
}
