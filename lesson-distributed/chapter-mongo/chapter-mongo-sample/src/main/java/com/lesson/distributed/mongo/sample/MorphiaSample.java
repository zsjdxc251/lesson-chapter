package com.lesson.distributed.mongo.sample;


import com.lesson.distributed.mongo.sample.entity.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;

/**
 * @author zhengshijun
 * @version created on 2018/9/5.
 */
public class MorphiaSample {

    public static void main(String[] args){
        final Morphia morphia = new Morphia();


        morphia.mapPackage("com.lesson.distributed.mongo.sample");
        ServerAddress serverAddress = new ServerAddress("120.76.24.4",27017);

        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("dev","bizcard-user-center","mw9fs9rsglADf98mc9".toCharArray());
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).threadsAllowedToBlockForConnectionMultiplier(40).build();
        MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);

        final Datastore datastore = morphia.createDatastore(mongoClient, "bizcard-user-center");

        Query<User> users = datastore.find(User.class);
        users.order(Sort.descending("ctime"));
        FindOptions findOptions = new FindOptions();
        findOptions.limit(10).skip(0);
        System.out.println(users.count());
        System.out.println(users.asList(findOptions).size());


        datastore.ensureIndexes();

    }
}
